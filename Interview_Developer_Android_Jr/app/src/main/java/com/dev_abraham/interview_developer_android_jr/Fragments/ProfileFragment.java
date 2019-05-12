package com.dev_abraham.interview_developer_android_jr.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dev_abraham.interview_developer_android_jr.Models.ModelProfile;
import com.dev_abraham.interview_developer_android_jr.R;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;
import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button btnCamera;
    private Button btnProfile;
    private ImageView ivProfile;
    private EditText etName;
    private EditText etAddress;
    private EditText etPhoneNumber;
    private EditText etEmail;


    private ModelProfile profileObject;
    private Boolean booleanProfile;

    private String mCurrentPhotoPath=null;
    private String fotoPathProfile;
    private  SharedPreferences.Editor editor ;
    private  SharedPreferences pref ;
    public static final String MY_PREFS_NAME = "profile";

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1000;
    private static final int GET_FILE_ACTIVITY_REQUEST_CODE = 2000;
    private OnFragmentInteractionListener mListener;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);


        btnCamera  = (Button) view.findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             takePhoto();
            }
        });


        btnProfile  = (Button) view.findViewById(R.id.btnSaveProfile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            saveProfile();
            }
        });


        ivProfile = (ImageView) view.findViewById(R.id.ivProfile);
        etName =(EditText) view.findViewById(R.id.etNameProfile);
        etName.setFocusable(false);
        etName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                etName.setFocusableInTouchMode(true);

                return false;
            }
        });
        etAddress=(EditText) view.findViewById(R.id.etAddressProfile);
        etAddress.setFocusable(false);
        etAddress.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                etAddress.setFocusableInTouchMode(true);

                return false;
            }
        });
        etPhoneNumber=(EditText) view.findViewById(R.id.etNumberProfile);
        etPhoneNumber.setFocusable(false);
        etPhoneNumber.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                etPhoneNumber.setFocusableInTouchMode(true);

                return false;
            }
        });
        etEmail =(EditText) view.findViewById(R.id.etEmailProfile);
        etEmail.setFocusable(false);
        etEmail.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                etEmail.setFocusableInTouchMode(true);

                return false;
            }
        });

        pref = getActivity().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        booleanProfile=pref.getBoolean("profileBoolean", false);

        if(booleanProfile){
            Gson gson = new Gson();
            String json = pref.getString("profileObject", "");
            profileObject = gson.fromJson(json, ModelProfile.class);
        }else{
            profileObject= new ModelProfile();
        }

        setValuesProfile(profileObject);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */


    private void selectImage() {

        final CharSequence[] options = { "Tomar Fotografia", "Elige una de la galeria","Cancelar" };

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("Agrega una foto de perfil");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Tomar Fotografia"))

                {
                    dispatchTakePictureIntent();
                }

                else if (options[item].equals("Elige una de la galeria"))
                {
                    Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, GET_FILE_ACTIVITY_REQUEST_CODE);

                }

                else if (options[item].equals("Cancelar")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }




    public void takePhoto(){

        if(isStoragePermissionGranted()){
            selectImage();
        }


    }

    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getActivity().checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG,"Permission is granted");
                return true;
            } else {

                Log.v(TAG,"Permission is revoked");
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG,"Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Log.v(TAG,"Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission
        }
    }



    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getContext(),
                        "com.example.ruaabraham.Interview_Developer_Android_Jr.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        }
    }

    private void displayFile(){
        File file = new File(fotoPathProfile);
        if(file.exists()){
            Bitmap bitmap = BitmapFactory.decodeFile(fotoPathProfile);
            int orientation =readPictureDegree(fotoPathProfile);
            ivProfile.setImageBitmap(bitmap);
            ivProfile.setRotation(orientation);
        }else{
            Toast.makeText(getContext(),"No se encontro foto de perfil",Toast.LENGTH_LONG).show();
        }


    }

    public static int readPictureDegree(String path) {
        int degree  = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "jpg_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                    Toast.makeText(getContext(),"Foto Actualizada",Toast.LENGTH_LONG).show();
                    fotoPathProfile=mCurrentPhotoPath;
                    displayFile();
            }else{
                File file = new File(mCurrentPhotoPath);
                file.delete();
                Toast.makeText(getContext(),"Cancelo la fotografia",Toast.LENGTH_LONG).show();

            }
        }else if (requestCode == GET_FILE_ACTIVITY_REQUEST_CODE){

            if (resultCode == Activity.RESULT_OK) {
                Uri selectedImage = data.getData();
                //;
                //imgui = selectedImage;
                String[] filePath = { MediaStore.Images.Media.DATA };

                Cursor c = getActivity().getContentResolver().query(selectedImage,filePath, null, null, null);

                c.moveToFirst();

                int columnIndex = c.getColumnIndex(filePath[0]);

                String picturePath = c.getString(columnIndex);

                c.close();
                fotoPathProfile=picturePath;
                Toast.makeText(getContext(),"Foto Actualizada",Toast.LENGTH_LONG).show();
                displayFile();
            }else{


            }
        }
    }


    public void saveProfile(){
        ModelProfile profile = new ModelProfile();
        //set variables of 'myObject', etc.

        profile.setPicture(fotoPathProfile);
        profile.setName(etName.getText().toString());
        profile.setAddress(etAddress.getText().toString());
        profile.setPhoneNumber(etPhoneNumber.getText().toString());
        profile.setEmail(etEmail.getText().toString());

        editor = pref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(profile);
        editor.putString("profileObject", json);
        editor.putBoolean("profileBoolean", true);
        Boolean save= editor.commit();
        if(save){
            Toast.makeText(getContext(),"Se guardo la informacion",Toast.LENGTH_LONG).show();

        }else{
            Toast.makeText(getContext(),"No se guardo la informacion,intente mas tarde",Toast.LENGTH_LONG).show();

        }

    }

    public void setValuesProfile(ModelProfile profile){
        fotoPathProfile=profile.getPicture();
        etName.setText(profile.getName());
        etAddress.setText(profile.getAddress());
        etPhoneNumber.setText(profile.getPhoneNumber());
        etEmail.setText(profile.getEmail());
        displayFile();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
