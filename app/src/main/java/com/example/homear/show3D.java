package com.example.homear;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.ar.core.exceptions.CameraNotAvailableException;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.HitTestResult;
import com.google.ar.sceneform.Scene;
import com.google.ar.sceneform.SceneView;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.ux.FootprintSelectionVisualizer;
import com.google.ar.sceneform.ux.TransformableNode;
import com.google.ar.sceneform.ux.TransformationSystem;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class show3D extends AppCompatActivity {

    private SceneView sceneView;
    private Scene scene;
    private TransformationSystem ts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show3_d);
        sceneView = findViewById(R.id.scene_View);
//        scene = sceneView.getScene();

//        TransformationSystem transformationSystem=
         ts=new TransformationSystem(getResources().getDisplayMetrics(),new FootprintSelectionVisualizer());
         sceneView.getScene().addOnUpdateListener(this::onFrameUpdate);
         sceneView.getRenderer().setClearColor(new com.google.ar.sceneform.rendering.Color(Color.LTGRAY));
         sceneView.getScene().addOnPeekTouchListener(new Scene.OnPeekTouchListener() {
             @Override
             public void onPeekTouch(HitTestResult hitTestResult, MotionEvent motionEvent) {
                 ts.onTouch(hitTestResult,motionEvent);
             }
         });
         sceneView.getScene().getCamera().setLocalPosition(new Vector3(0,0.2f,0));






        FirebaseApp.initializeApp(this);
        FirebaseStorage storage = FirebaseStorage.getInstance();
//
        StorageReference model = storage.getReference().child("SHOE_CABINET.glb");
//        Camera camera = sceneView.getScene().getCamera();
//        camera.setLocalRotation(Quaternion.axisAngle(Vector3.right(), -30.0f));

        findViewById(R.id.addition).setOnClickListener(v->{

            try {
                File file = File.createTempFile("chairrr", "glb");
                model.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        createScene(file);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
//

            }

        });

//        sceneView.getScene().addOnPeekTouchListener(new Scene.OnPeekTouchListener() {
//            @Override
//            public void onPeekTouch(HitTestResult hitTestResult, MotionEvent motionEvent) {
//                try {
//                    ts.onTouch(hitTestResult,motionEvent);
//
//                }
//                catch(Exception e){
//                    e.printStackTrace();
//                }
//
//            }
//        });



    }
    private void onFrameUpdate(FrameTime frameTime){
        if(!modelplaced){
            onRenderable(renderable);
        }
    }
    private Renderable renderable;
    private void createScene(File file) {
//        scene= new Scene(sceneView);
//        sceneView.getScene();
        RenderableSource renderableSource = RenderableSource
                .builder()
                .setSource(this, Uri.parse(file.getPath()), RenderableSource.SourceType.GLB)
                .setScale(0.3f)
                .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                .build();


        ModelRenderable.builder()
                .setSource(this,renderableSource)
                .setRegistryId(file.getPath())
                .build()

                .thenAccept(renderable -> onRenderable(renderable))
                .exceptionally(throwable -> {
                    Log.i("Sceneform","failed to load");
//                    Toast toast =
//                            Toast.makeText(this, "Unable to load model", Toast.LENGTH_LONG);
//                    toast.setGravity(Gravity.CENTER, 0, 0);
//                    toast.show();
                    return null;
                });
                renderable=renderable;


    }
    private boolean modelplaced=false;
    private void onRenderable(Renderable renderable) {
//        Node modelNode = new Node();
//        /*TransformableNode tn=new TransformableNode(ts);
//        modelNode.setParent(tn);
//        tn.setParent(sceneView.getScene());*/
//        modelNode.setParent(sceneView.getScene());
//        TransformableNode model =new TransformableNode(ts);
//        model.setParent(modelNode);
//        model.setRenderable(renderable);
////      /*  tn.getRotationController().isEnabled();
////        tn.getScaleController().isEnabled();
////        tn.getTranslationController().isEnabled();
////        tn.setRenderable(renderable);*/
//          model.setLocalPosition(new Vector3(0f, -0.2f, -0.6f));
//
////       /* modelNode.setLocalPosition(new Vector3(0f, 0f, -1f));
////        modelNode.setLocalScale(new Vector3(3f, 3f, 3f));*/
//          model.select();
//

//        /*sceneView.getScene().addChild(tn);*/
//
//            if(sceneView!=null){
//                TransformableNode tn=new TransformableNode(ts);
//                tn=renderable;
//
//
//            }
//
        TransformableNode modelNode=new TransformableNode(ts);
        modelNode.getScaleController().setEnabled(true);
        modelNode.getTranslationController().setEnabled(false);
        modelNode.getRotationController().setEnabled(true);
//        modelNode.setLocalRotation(Quaternion.axisAngle(new Vector3(0f, 0f, 1f), 90f));
        modelNode.setRenderable(renderable);
        sceneView.getScene().addChild(modelNode);
        modelNode.setLocalPosition(new Vector3(0f,-0.05f,-0.6f));


        modelNode.select();
        modelplaced=true;




    }
    @Override
    protected void onResume() {
        super.onResume();
        try {
            sceneView.resume();
        }
        catch (CameraNotAvailableException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sceneView.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sceneView.destroy();
    }
}
