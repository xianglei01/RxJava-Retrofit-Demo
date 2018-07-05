package com.example.data.request;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.example.domain.listener.CallBack;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author xianglei
 * @created 2018/7/5 13:27
 */
public class DownRequest {

    public static void download(final Context context, final byte[] response, final String saveDir, CallBack<File> callBack) {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        try {
//            is = response.byteStream();
//            //apk保存路径
//            final String fileDir = isExistDir(saveDir);
//            //文件
//            File file = new File(fileDir, getNameFromUrl(url));
//            context.runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    Toast.makeText(context, "下载成功:" + fileDir + "," + getNameFromUrl(url), Toast.LENGTH_SHORT).show();
//                }
//            });
//            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }

            fos.flush();
            //apk下载完成后 调用系统的安装方法
//            Intent intent = new Intent(Intent.ACTION_VIEW);
//            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
//            context.startActivity(intent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            if (is != null) {
//                is.close();
//            }
//            if (fos != null) {
//                fos.close();
//            }


        }
    }

    /**
     * @param saveDir
     * @return
     * @throws IOException 判断下载目录是否存在
     */
    public static String isExistDir(String saveDir) throws IOException {
        // 下载位置
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            File downloadFile = new File(Environment.getExternalStorageDirectory(), saveDir);
            if (!downloadFile.mkdirs()) {
                downloadFile.createNewFile();
            }
            String savePath = downloadFile.getAbsolutePath();
            Log.e("savePath", savePath);
            return savePath;
        }
        return null;
    }

    /**
     * @param url
     * @return 从下载连接中解析出文件名
     */
    private static String getNameFromUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }
}
