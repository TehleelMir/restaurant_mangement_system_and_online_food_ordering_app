package com.example.finalyearproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.finalyearproject.utility.AdminLoginModel;
import com.example.finalyearproject.utility.firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Admin_FragmenForProfile extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.admin_fragment_for_profile , container , false);
        setListener(view);
        return view;
    }

    private void setListener(View view){
        DatabaseReference reference = firebase.getAdminDetail();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                AdminLoginModel temp = null;
                for(DataSnapshot x : snapshot.getChildren()){
                    temp = snapshot.getValue(AdminLoginModel.class);
                }
                if(temp != null) {
                    ((TextView) view.findViewById(R.id.profile_name)).
                            setText(temp.getName());
                    ((TextView) view.findViewById(R.id.profile_email)).
                            setText(temp.getEmail());
                    ImageView imageView = view.findViewById(R.id.profile_image_admin);
                    Picasso.get().load(temp.getUri()).fit().centerCrop().into(imageView);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        view.findViewById(R.id.profile_change_password).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText temp = new EditText(view.getContext());
                final AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                dialog.setTitle("Reset Password");
                dialog.setMessage("Enter your email to receive reset link");
                dialog.setView(temp);

                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String mail = temp.getText().toString();
                        FirebaseAuth auth = firebase.resetPassword();
                        auth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(view.getContext(), "reseet link send to your password", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(view.getContext(), "errrorrrrrrrrrr", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                dialog.show();

            }
        });

        view.findViewById(R.id.profile_logout_admin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebase.adminLogout(getContext());
                startActivity(new Intent(getActivity() , welcomeActivity.class));
                getActivity().finish();
            }
        });
    }
}
