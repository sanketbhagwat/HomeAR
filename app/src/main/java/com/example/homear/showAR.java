package com.example.homear;

import android.Manifest;
import android.app.AlertDialog;
import android.app.MediaRouteButton;
import android.content.pm.PackageManager;
import android.media.CamcorderProfile;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.rendering.*;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class showAR extends AppCompatActivity {

    private final static int MY_REQUEST_CODE = 1;
    private MediaRouteButton mArButton;
    private ArFragment arCam;
    private int clickNo = 0;
    private VideoRecorder videoRecorder;
//    private View colorPreview;
//    private int defaultColor;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);


        setContentView(R.layout.activity_show_ar);

//        Intent intent=getIntent();
//        String path =  intent.getExtras().get("modelFile").toString();
//
//        buildModel(path);




        FirebaseApp.initializeApp(this);
        FirebaseStorage storage = FirebaseStorage.getInstance();

        StorageReference model = storage.getReference().child("SHOE_CABINET.glb");
        arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);


        findViewById(R.id.closeButton).setOnClickListener(e -> {
            finish();
        });

        findViewById(R.id.vdoRecording).setOnClickListener(e->{
            if(videoRecorder==null) {
                videoRecorder = new VideoRecorder();
                videoRecorder.setSceneView(arCam.getArSceneView());
                int orientation = getResources().getConfiguration().orientation;
                videoRecorder.setVideoQuality(CamcorderProfile.QUALITY_HIGH, orientation);
            }
            boolean isRecording=videoRecorder.onToggleRecord();
            if(isRecording)
                Toast.makeText(this,"Started Recording",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this,"Recording stopped",Toast.LENGTH_SHORT).show();


        });

//        findViewById(R.id.loadButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(showAR.this,models.class);
//                registerForActivityResult()
//
//            }
//        })



        findViewById(R.id.loadButton)
                .setOnClickListener(v -> {
                    try {
                        File file = File.createTempFile("chair", "glb");
                        model.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                buildModel(file);
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
//

                    }
//
                });


//        findViewById(R.id.changeTexture).setOnClickListener((e->{
//                ColorPickerDialog.Builder dialog = new ColorPickerDialog.Builder(this);
//                dialog.setTitle("Choose a color");
//                dialog.setNegativeButton(R.string.cancel, (dialog1, which) -> {
//                });
//                dialog.setPositiveButton(getString(R.string.ok), colorEnvelope -> {
//                    BaseTransformableNode node = arFragment.getTransformationSystem().getSelectedNode();
//                    if (node != null) {
//                        node.getRenderable().getMaterial().setFloat3("baseColorTint", new Color(colorEnvelope.getColor()));
////        }
////    });
////    dialog.show();
////}
//
//        }));


    }

    private ModelRenderable renderable;

    private void buildModel(File file) {
        RenderableSource renderableSource = RenderableSource
                .builder()
                .setSource(this, Uri.parse(file.getPath()), RenderableSource.SourceType.GLB)
                .setScale(0.6f)
                .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                .build();
        arCam.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            Anchor anchor = hitResult.createAnchor();
//            anchorNode.setRenderable(renderable);
//            arCam.getArSceneView().getScene().addChild(anchorNode);


            ModelRenderable.builder()
                    .setSource(this,renderableSource)
                    .build()
                    .thenAccept(modelRenderable -> { addModel(anchor, modelRenderable);
                        Toast.makeText(this,"model built",Toast.LENGTH_SHORT).show();
                        renderable=modelRenderable;
                    })
                    .exceptionally(throwable -> {
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setMessage("Something is not right" + throwable.getMessage()).show();
                        return null;


                    });
        });
    }




    private void addModel(Anchor anchor, ModelRenderable modelRenderable) {
        AnchorNode anchorNode = new AnchorNode(anchor);
//        renderable=modelRenderable;


        // attaching the anchorNode with the ArFragment
        anchorNode.setParent(arCam.getArSceneView().getScene());

        // attaching the anchorNode with the TransformableNode
        TransformableNode model = new TransformableNode(arCam.getTransformationSystem());
        model.setParent(anchorNode);

//        Node downword=new Node();
//        downword.setParent(model);
//        downword.setLookDirection(new Vector3(0f,0f,0f));
//        downword.setRenderable(modelRenderable);

        // attaching the 3d model with the TransformableNode
        // that is already attached with the node
        model.setRenderable(modelRenderable);
//        TranslationController controller=model.getTranslationController();


        model.select();
//        addHighlightToNode(model);
//


    }
    @Override
    public void onResume(){
        super.onResume();
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                !=PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);


    }





        private void addHighlightToNode(Node node) {
            CompletableFuture<Material> materialCompletableFuture =
                    MaterialFactory.makeOpaqueWithColor(this, new Color(0, 255, 244));

            ((CompletableFuture<?>) materialCompletableFuture).thenAccept(material -> {
                Renderable r2 = node.getRenderable().makeCopy();
                r2.setMaterial((Material) material);
                node.setRenderable(r2);
            });
        }

    }

