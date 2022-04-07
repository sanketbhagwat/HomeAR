package com.example.homear;

import android.Manifest;
import android.app.AlertDialog;
import android.app.MediaRouteButton;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.media.CamcorderProfile;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.HitTestResult;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.*;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.concurrent.CompletableFuture;

public class showAR extends AppCompatActivity implements showARR {

    private final static int MY_REQUEST_CODE = 1;
    private MediaRouteButton mArButton;
    private ArFragment arCam;
    private int clickNo = 0;
    private VideoRecorder videoRecorder;
    private BottomSheetDialog bottomSheetDialog;
    private BottomSheetBehavior bottomSheetBehavior;
    private FloatingActionButton loadButton;
    private Node currentNode = null;
    ViewRenderable viewRenderable;
    ImageButton ar_Button;
    Node arButton;

//    private View colorPreview;
//    private int defaultColor;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);


        setContentView(R.layout.activity_show_ar);

//        Button dialogbtn=findViewById(R.id.simpleDialog);
//        dialogbtn.setOnClickListener(view -> {
//            simpleDialog();
//        });

//        CompletableFuture<ViewRenderable> modelEditor=ViewRenderable.builder().setView(this,R.layout.ar_button).build();



//        String[] materials=new String[]{"Material-01","Material-02","Material-03"};
//        ArrayAdapter<String> madapter=new ArrayAdapter<>(
//                this,R.layout.dropdown_items,materials
//        );
//        AutoCompleteTextView autoCompleteTextView1=findViewById(R.id.field_materials);
//        autoCompleteTextView1.setAdapter(madapter);
//
//        String[] textures=new String[]{"texture-01","texture-02","texture-02"};
//        ArrayAdapter<String>tadapter=new ArrayAdapter<>(
//                this,R.layout.dropdown_items,textures
//        );
//        AutoCompleteTextView autoCompleteTextView2 =findViewById(R.id.field_textures);
//        autoCompleteTextView2.setAdapter(tadapter);
//
//        autoCompleteTextView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(showAR.this,autoCompleteTextView1.getText().toString(),Toast.LENGTH_SHORT).show();
//            }
//        });
//        autoCompleteTextView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(showAR.this,autoCompleteTextView2.getText().toString(),Toast.LENGTH_SHORT).show();
//            }
//        });


//

        if(Build.VERSION.SDK_INT>=21){
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            Window window=getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        }




        FirebaseApp.initializeApp(this);
        FirebaseStorage storage = FirebaseStorage.getInstance();

        StorageReference model = storage.getReference().child("SHOE_CABINET.glb");
        arCam = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arCameraArea);


        findViewById(R.id.closeButton).setOnClickListener(e -> {
            finish();
        });

        ImageButton vdoButton=findViewById(R.id.vdoRecording);
        vdoButton.setOnClickListener(e->{
            if(videoRecorder==null) {
                videoRecorder = new VideoRecorder();
                videoRecorder.setSceneView(arCam.getArSceneView());
                int orientation = getResources().getConfiguration().orientation;
                videoRecorder.setVideoQuality(CamcorderProfile.QUALITY_HIGH, orientation);
            }
            boolean isRecording=videoRecorder.onToggleRecord();
            if(isRecording) {
                Toast.makeText(this, "Started Recording", Toast.LENGTH_SHORT).show();
                vdoButton.setImageResource(R.drawable.outline_videocam_24);

            }

            else{
                Toast.makeText(this, "Recording stopped", Toast.LENGTH_SHORT).show();
                vdoButton.setImageResource(R.drawable.outline_videocam_off_24);

            }


        });

//



//        findViewById(R.id.loadButton)
//                .setOnClickListener(v -> {
//                    try {
//                        File file = File.createTempFile("chair", "glb");
//                        model.getFile(file).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//                            @Override
//                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                                buildModel(file);
//                            }
//                        });
//                    } catch (IOException e) {
//                        e.printStackTrace();
////
//
//                    }
////
//                });
        LinearLayout bottomSheetLayout=findViewById(R.id.bottom_sheet);
        bottomSheetBehavior=BottomSheetBehavior.from(bottomSheetLayout);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);



        bottomSheetBehavior.setPeekHeight(0);
        bottomSheetBehavior.setHideable(true);

        bottomSheetLayout.findViewById(R.id.getCan).setOnClickListener(new View.OnClickListener() {
                                                                           @Override
                                                                           public void onClick(View view) {
                                                                               Toast.makeText(showAR.this, "gett", Toast.LENGTH_SHORT).show();
                                                                               bottomSheetBehavior.setHideable(true);
                                                                               bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//                                                                               bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
//                                                                               findViewById(R.id.bottomLayout).setVisibility(View.VISIBLE);
                                                                           }
                                                                       });

        loadButton=findViewById(R.id.loadButton);
                loadButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        bottomSheetDialog = new BottomSheetDialog(showAR.this, R.style.BottomSheetTheme);
//                        View sheetView= LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet,
//                                 findViewById(R.id.bottom_sheet));

//                        sheetView.findViewById(R.id.getCan).setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                Toast.makeText(showAR.this,"gett",Toast.LENGTH_SHORT).show();
//                            }
                        bottomSheetBehavior.setPeekHeight(900);
                        bottomSheetBehavior.setHideable(false);
//                        loadButton.setVisibility(View.INVISIBLE);
                        findViewById(R.id.bottomLayout).setVisibility(View.INVISIBLE);


//                        bottomSheetDialog.setContentView(sheetView);
//                        bottomSheetDialog.show();

                    }
                });
//                    bottomSheetBehavior.setPeekHeight(280);
//                    bottomSheetBehavior.setHideable(false);




        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState==BottomSheetBehavior.STATE_HIDDEN) {
//                    bottomSheetBehavior.setPeekHeight(0);
                    findViewById(R.id.bottomLayout).setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                findViewById(R.id.bottomLayout).setVisibility(View.INVISIBLE);


            }
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
        ViewRenderable.builder()
                .setView(this,R.layout.ar_button)
                .build()
                .thenAccept(renderable->{viewRenderable=renderable;
                    renderable.setShadowCaster(false);
                    renderable.setShadowReceiver(false);


                });


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


    private boolean handleTouch(HitTestResult hitTestResult, MotionEvent motionEvent){
        Log.e("manual",this. getClass(). getSimpleName()+" "+"inside handleTouch");
        // arFragment.onPeekTouch(hitTestResult,motionEvent);
        if (hitTestResult.getNode() != null) {
            Log.e("manual",this. getClass(). getSimpleName()+" "+"clicked on node");
//            Toast.makeText(this,"tappp",Toast.LENGTH_SHORT).show();




        }
        return true;
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
//        model.setOnTouchListener(this::handleTouch);
//        if(model.select())
        currentNode=model;
        arButton=new Node();
        arButton.setParent(anchorNode);

        arButton.setOnTapListener(new Node.OnTapListener() {
            @Override
            public void onTap(HitTestResult hitTestResult, MotionEvent motionEvent) {
                simpleDialog();
                Log.e("manual",currentNode.toString());

            }
        });
        model.setOnTapListener(this::handleTap);
//        addButton(anchorNode,model);


//        addHighlightToNode(model);
//


    }

    private void handleTap(HitTestResult hitTestResult, MotionEvent motionEvent){
        Log.e("manual",this. getClass(). getSimpleName()+" "+"inside handleTap");
        // arFragment.onPeekTouch(hitTestResult,motionEvent);
        if (hitTestResult.getNode() != null) {
            Log.e("manual",this. getClass(). getSimpleName()+" "+"clicked on node");
            arButton.setRenderable(viewRenderable);
            arButton.setLocalPosition(new Vector3(0.0f,currentNode.getLocalPosition().y+0.5f, 0.0f));
//            ViewRenderable.builder()
//                    .setView(this,R.layout.ar_button)
//                    .build()
//                    .thenAccept(renderable->{viewRenderable=renderable;
//                        renderable.setShadowCaster(false);
//                        renderable.setShadowReceiver(false);
//
//
//                    });

        }
    }


    private void addButton(AnchorNode anchorNode, TransformableNode model) {
//        TransformableNode arButton = new TransformableNode(arCam.getTransformationSystem());

//        Node arButton=new Node();
//        arButton.setParent(anchorNode);
//
//        arButton.setOnTapListener(new Node.OnTapListener() {
//            @Override
//            public void onTap(HitTestResult hitTestResult, MotionEvent motionEvent) {
//                simpleDialog();
//                Log.e("manual",currentNode.toString());
//
//            }
//        });
//        arButton.setRenderable(viewRenderable);
//        arButton.setLocalPosition(new Vector3(0.0f,model.getLocalPosition().y+0.5f, 0.0f));

//        arButton.select();
//        findViewById(R.id.ar_button).setOnClickListener(view -> {
//            Toast.makeText(this,"click",Toast.LENGTH_SHORT).show();
//            simpleDialog();
//
//                }

//        View arBtnView=viewRenderable.getView();
//        ImageButton ar_btn=(ImageButton) arBtnView.findViewById(R.id.ar_button);
//        ar_btn.setOnClickListener(v->{
//            Toast.makeText(this,"click"+currentNode.toString(),Toast.LENGTH_SHORT).show();
//            Log.e("manual",currentNode.toString());
////            simpleDialog();
////            showControls(anchorNode,model);
//
////            arButton.setLocalScale(new Vector3(0,0,0));
////            simpleDialog();
//
//        });




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

//        Texture.builder()
//                .setSource(this, texturesInteractor.getRes(position))
//                    .build()
//                    .thenAccept(t -> {
//                BaseTransformableNode node = arFragment.getTransformationSystem().getSelectedNode();
//                if (node != null) {
//                    node.getRenderable().getMaterial().setTexture("baseColor", t);
//                }
//            })
//            .exceptionally(this::showModelBuildError);

//         Texture.builder()
//                 .setSource(this, R.drawable.texture)
//  .build()
//  .thenAccept { texture ->
//            MaterialFactory.makeOpaqueWithTexture(this, texture)
//                    .thenAccept { materialTexture = it}
//    }

//    Texture texture=R.drawable.texture;
//    private void changeTextureNode(Node node) {
//        CompletableFuture<Material> materialCompletableFuture =
//                MaterialFactory.makeOpaqueWithTexture(this)
//
//        ((CompletableFuture<?>) materialCompletableFuture).thenAccept(material -> {
//            Renderable r2 = node.getRenderable().makeCopy();
//            r2.setMaterial((Material) material);
//            node.setRenderable(r2);
//        });
//    }

    private void simpleDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(showAR.this).create();
        alertDialog.setTitle("Simple AlertDialog"); alertDialog.setMessage("please click this option");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); } }); alertDialog.show();
    }



//    @Override
//    public void onUpdate(FrameTime frameTime){
//
//    }



    }

