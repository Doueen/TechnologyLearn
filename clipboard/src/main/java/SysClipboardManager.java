import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * @author ZhangHongzheng
 * @description
 * @create 2023-01-31 15:04
 */
public class SysClipboardManager {
    public static void main(String[] args)  {
       getSysClipboardText();
    }
    public static String getSysClipboardText() {
        String ret = "";
        Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
        // ��ȡ���а��е�����
        Transferable clipTf = sysClip.getContents(null);

        if (clipTf != null) {
            // ��������Ƿ����ı�����
            if (clipTf.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                try {
                    ret = (String) clipTf.getTransferData(DataFlavor.stringFlavor);
                } catch (Exception e) {

                }
            }
        }
        return ret;
    }


}
