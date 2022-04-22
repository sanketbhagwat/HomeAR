package com.example.homear;

import com.google.ar.sceneform.ux.ArFragment;

public class ImageCapture {

    private ArFragment fragment;
//
//
//    private String generateFilename() {
//        String date =
//                new SimpleDateFormat("yyyyMMddHHmmss", java.util.Locale.getDefault()).format(new Date());
//        return Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES) + File.separator + "Sceneform/" + date + "_screenshot.jpg";
//    }
//
//    private void saveBitmapToDisk(Bitmap bitmap, String filename) throws IOException {
//
//        File out = new File(filename);
//        if (!out.getParentFile().exists()) {
//            out.getParentFile().mkdirs();
//        }
//        try (FileOutputStream outputStream = new FileOutputStream(filename);
//             ByteArrayOutputStream outputData = new ByteArrayOutputStream()) {
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputData);
//            outputData.writeTo(outputStream);
//            outputStream.flush();
//            outputStream.close();
//        } catch (IOException ex) {
//            throw new IOException("Failed to save bitmap to disk", ex);
//        }
//    }
//    private void takePhoto() {
//        final String filename = generateFilename();
//        ArSceneView view = fragment.getArSceneView();
//
//        // Create a bitmap the size of the scene view.
//        final Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),
//                Bitmap.Config.ARGB_8888);
//
//        // Create a handler thread to offload the processing of the image.
//        final HandlerThread handlerThread = new HandlerThread("PixelCopier");
//        handlerThread.start();
//        // Make the request to copy.
//        PixelCopy.request(view, bitmap, (copyResult) -> {
//            if (copyResult == PixelCopy.SUCCESS) {
//                try {
//                    saveBitmapToDisk(bitmap, filename);
//                } catch (IOException e) {
//                    Toast toast = Toast.makeText(this, e.toString(),
//                            Toast.LENGTH_LONG);
//                    toast.show();
//                    return;
//                }
//                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),
//                        "Photo saved", Snackbar.LENGTH_LONG);
//                snackbar.setAction("Open in Photos", v -> {
//                    File photoFile = new File(filename);
//
//                    Uri photoURI = FileProvider.getUriForFile(MainActivity.this,
//                            MainActivity.this.getPackageName() + ".ar.codelab.name.provider",
//                            photoFile);
//                    Intent intent = new Intent(Intent.ACTION_VIEW, photoURI);
//                    intent.setDataAndType(photoURI, "image/*");
//                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                    startActivity(intent);
//
//                });
//                snackbar.show();
//            } else {
//                Toast toast = Toast.makeText(MainActivity.this,
//                        "Failed to copyPixels: " + copyResult, Toast.LENGTH_LONG);
//                toast.show();
//            }
//            handlerThread.quitSafely();
//        }, new Handler(handlerThread.getLooper()));
//    }
}
