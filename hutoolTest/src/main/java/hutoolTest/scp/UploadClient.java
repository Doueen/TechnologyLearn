package hutoolTest.scp;

import cn.hutool.extra.ssh.JschUtil;
import cn.hutool.extra.ssh.Sftp;
import com.jcraft.jsch.Session;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;

/**
 * 上传客户端
 *
 * @author Mr.Wang
 * @date 2022-09-22 15:52:26
 */
public class UploadClient {

    private static final String prefix = "";

    private static Sftp sftp;

    private static Map<String,String>  uploadConfig = new HashMap<>();

    private static Map<String,List<String>>  ignoreMapList = new HashMap<>();

    private static Map<String,String> otherData = new HashMap<>();

    private static String projectDir;

    private static String host;

    static {
        Properties properties = new Properties();
        try{
            properties.load(new InputStreamReader(new FileInputStream(prefix + "config.properties"), Charset.forName("GBK")));
            host = properties.getProperty("host");
            String password = properties.getProperty("password");
            String port = properties.getProperty("port","22");
            projectDir = properties.getProperty("project");
            sftp = new Sftp(host,Integer.parseInt(port),"root",password);
            Enumeration<?> enumeration = properties.propertyNames();
            while (enumeration.hasMoreElements()){
                String key = (String)enumeration.nextElement();
                String property = properties.getProperty(key);
                if(key.startsWith("upload.")){
                    String index = key.substring("upload.".length());
                    uploadConfig.put(index,property);
                }else if(key.startsWith("ignore.")){
                    String index = key.substring("ignore.".length());
                    List<String> strings = Arrays.asList(property.split(";"));
                    ignoreMapList.put(index,strings);
                }else{
                    otherData.put(key,property);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getHost(){
        return host;
    }


    public static void startUpload(){
        File project = new File(projectDir);
        if(project.exists() && project.isDirectory()){
            File[] files = project.listFiles((dir, name) -> {
                File children = new File(dir, name);
                return !(name.startsWith(".") || !children.isDirectory());
            });
            // 这是要上传的jarList
            List<File> allJarFile = getAllJarFile(files);
            uploadServerFolder(allJarFile);
        }else{
            System.out.println("项目目录不存在:"+projectDir);
        }
    }


    public static void startUploadJspFile(){
        File project = new File(projectDir,"webui/src/main/webapp");
        if(project.exists() && project.isDirectory()){
            File[] files = project.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    File children = new File(dir, name);
                    return children.isFile() && name.endsWith(".jsp");
                }
            });
            // 这是要上传的jarList
            Optional.ofNullable(files).ifPresent(jsp -> {
                uploadWebJsp(Arrays.asList(jsp));
                }
            );
        }else{
            System.out.println("webapps目录不存在:"+ project.getAbsolutePath());
        }
    }

    private static List<File> getAllJarFile(File[] files){
        List<File> jarFiles = new ArrayList<>();
        Arrays.stream(files).forEach(file -> {
            File target = new File(file, "target");
            if(target.exists()){
                Arrays.stream(Objects.requireNonNull(target.listFiles())).forEach(child ->{
                    if(child.isFile() && child.getName().endsWith(".jar")){
                        jarFiles.add(child);
                    }
                });
            }
        });
        return jarFiles;
    }

    public static void restartTomcat(){
        try{
            Session session = sftp.getClient().getSession();
            String exec = JschUtil.exec(session, "ps -ef | grep bootstrap | grep -v grep", Charset.defaultCharset());
            String[] split = exec.split("\\s+");
            if(split.length > 3){
                // 存在tomcat进程
                JschUtil.exec(session, "ps -ef | grep bootstrap | grep -v grep|awk '{print $2}' |xargs kill -9", Charset.defaultCharset());
            }
            String exec1 = JschUtil.exec(session, "cd /opt/infosec/NetSignServer52/WebServer/bin && ./startup.sh", Charset.defaultCharset());
            System.out.println(exec1);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void restartNetSignServer(){
        try{
            Session session = sftp.getClient().getSession();
            JschUtil.exec(session,"cd /opt/infosec/NetSignServer52/NetSignServer && ./NetSignServer.sh -restart",Charset.defaultCharset());
            System.out.println("重启NetSignServer完成，请稍等...");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * 上传文件到指定文件夹下  upload.xxx 的文件夹
     *
     * @param allJars 所有罐子
     */
    private static void  uploadServerFolder(List<File> allJars){
        for (Map.Entry<String,String> entry: uploadConfig.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println("开始上传文件  ->  "+value);
            Iterator<File> iterator = allJars.iterator();
            List<String> ignoreList = ignoreMapList.get(key);
            while (iterator.hasNext()){
                // 需要上传的jar
                File uploadJar = iterator.next();
                String jarName = uploadJar.getName();
                if(ignoreList == null || !ignoreList.contains(jarName)){
                    boolean upload = sftp.upload(value, uploadJar);
                    System.out.printf("上传文件: %s  ----> %s ; %s%n",jarName,value,upload);
                }
            }
            System.out.println("上传文件结束  ->  "+value);
        }
    }

    private static void  uploadWebJsp(List<File> allJsp){
        String web = otherData.get("web");
        if(web  == null){
            System.out.println("没有指定webui目录，上传终止..");
            return;
        }
        List<String> ls = sftp.ls(web);
        int jspCount = 0;
        for (String file: ls){
            if(file.endsWith(".jsp")){
                if(jspCount++ > 10){
                    break;
                }
            }
        }
        if(jspCount < 10){
            System.out.println(web +"目录下jsp文件少于10个，判定webui目录失败,上传终止..");
            return;
        }
        System.out.println("-------------------开始上传jsp-------------------");
        for (File jsp: allJsp){
            boolean upload = sftp.upload(web, jsp);
            System.out.printf("上传jsp文件: %s  ----> %s ; %s%n",jsp.getName(),web,upload);
        }
        System.out.println("-------------------上传结束--------------------------");
    }

    public static void main(String[] args) {
        System.out.println("是否上传文件从: "+projectDir + " 到 " + host + " ? [y/n]");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if("y".equals(s)){
            startUpload();
        }
        String web = otherData.get("web");
        File project = new File(projectDir,"webui/src/main/webapp");
        if(web != null && project.exists() && project.isDirectory()){
            System.out.println("是否上传文件从: "+project.getAbsolutePath() + " 到 " + host + " ? [y/n]");
            s = scanner.nextLine();
            if("y".equals(s)){
                startUploadJspFile();
            }
            System.out.println("是否重新启动Tomcat ? [y/n]");
            s = scanner.nextLine();
            if("y".equals(s)){
                restartTomcat();
            }
        }
        System.out.println("是否重新启动NetSignServer进程 ? [y/n]");
        s = scanner.nextLine();
        if("y".equals(s)){
            restartNetSignServer();
        }
        System.exit(0);
    }

}
