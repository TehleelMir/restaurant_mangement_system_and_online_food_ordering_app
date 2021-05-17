package com.example.finalyearproject.utility;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.finalyearproject.Customer;
import com.example.finalyearproject.Employ;
import com.example.finalyearproject.admin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.rpc.context.AttributeContext;
import com.squareup.okhttp.internal.DiskLruCache;

import java.util.List;
import java.util.UUID;

import static java.security.AccessController.getContext;

public class firebase {
    private static FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private static FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private static final String SHARED_PREF_FILE_NAME = "save login data" , WHO_IS_LOGIN = "whoIsLogin" , SAVE_EMPLOY="save employ";

    private firebase() {
    }

    private static ProgressDialog showProgressBar(Context context){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        return progressDialog;
    }

    private static void hideProgressBar(ProgressDialog progressDialog){
        progressDialog.dismiss();
    }

    public static void isAdminValid(String email, String password, Context context) {
        ProgressDialog progressDialog = showProgressBar(context);
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    DatabaseReference reference = firebaseDatabase.getReference("user type").child(firebaseAuth.getCurrentUser().getUid());
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.getValue() == null){
                                Toast.makeText(context, "email is vaid but not as admin", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            String temp = snapshot.child("type").getValue().toString();
                            if (temp.equals("admin")) {
                                setValueToSharedPref("ADMIN" , context);
                                context.startActivity(new Intent(context, admin.class));
                            } else
                                Toast.makeText(context, "email is vaid but not as admin", Toast.LENGTH_SHORT).show();
                            hideProgressBar(progressDialog);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                } else {
                    hideProgressBar(progressDialog);
                    Toast.makeText(context, "wrong email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void isCustomerValid(String email , String password , Context context){
        firebaseAuth.signInWithEmailAndPassword(email , password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    DatabaseReference reference = firebaseDatabase.getReference().child("customers account").child(firebaseAuth.getCurrentUser().getUid());
                    String x = firebaseAuth.getCurrentUser().getUid();
                    reference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.getValue() == null){
                                Toast.makeText(context, "email is vaid but not as adminnnnnnnn", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            String temp = snapshot.child("type").getValue().toString();
                            if (temp.equals("customer")) {
                                setValueToSharedPref("CUSTOMER" , context);
                                context.startActivity(new Intent(context, Customer.class));
                            } else
                                Toast.makeText(context, "email is vaid but not as admin", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                } else
                    Toast.makeText(context, "wrong email or password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void isEmployValid(String id , Context context){
        DatabaseReference reference = firebaseDatabase.getReference("employ").child(id);
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.getValue() == null){
                        Toast.makeText(context, "the id is flaseee", Toast.LENGTH_SHORT).show();
                    }else {
                        String temp = snapshot.getValue().toString();
                        //Toast.makeText(context, "hello " + temp, Toast.LENGTH_SHORT).show();
                        Intent temp2 = new Intent(context , Employ.class);
                        temp2.putExtra("id" , id);
                        temp2.putExtra("name" , temp);
                        setValueToSharedPref("EMPLOY" , context);
                        saveEmploy(id , context);
                        context.startActivity(temp2);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
    }

    public static void addNewEmploy(String name, String id, Context context) {
        firebaseDatabase.getReference().child("employ").child(id).setValue(name);
    }

    public static void registerNewCustomer(String email , String password , CustomerDetail_model obj , Context context){
        firebaseAuth.createUserWithEmailAndPassword(email , password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                String key = firebaseAuth.getCurrentUser().getUid();
                obj.setKey(key);
                String token = FirebaseInstanceId.getInstance().getToken();
                obj.setToken(token);
                firebaseDatabase.getReference().child("customers account").child(key).setValue(obj);
                Toast.makeText(context, "user created succesfully", Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context , Customer.class));
            }
        });
    }

    public static void pushOrder(TakeOrderModel obj){
        String key = firebaseDatabase.getReference().child("queue orders").push().getKey();
        obj.setKey(key);
        firebaseDatabase.getReference().child("queue orders").child(key).setValue(obj);
    }

    public static DatabaseReference getAllEmployList() {
        return firebaseDatabase.getReference().child("employ");
    }

    public static DatabaseReference getAllOrderOnQueue(){
        return firebaseDatabase.getReference().child("queue orders");
    }

    public static DatabaseReference getAllOnlineOrder(){
        return firebaseDatabase.getReference().child("Online order queue");
    }

    public static void orderDone(String key){
        firebaseDatabase.getReference().child("queue orders").child(key).removeValue();
    }

    public static DatabaseReference getAllFoodItems(String cardNo) {
        return firebaseDatabase.getReference().child("food items").child(cardNo);
    }

    public static void deleteEmploy(String id , Context context) {
        firebaseDatabase.getReference().child("employ").child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(context, "employ deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void deleteFoodItem(String name, String imageName, String cardNo, Context context) {
        firebaseDatabase.getReference().child("food items").child(cardNo).child(name).removeValue();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference reference = storage.getReference("images/" + imageName);
        reference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(context, "Food item deleted", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static void editEmploy(String id, String name) {
        firebaseDatabase.getReference().child("employ").child(id).setValue(name);
    }

    public static DatabaseReference getAdminDetail() {
        String id = firebaseAuth.getCurrentUser().getUid();
        return firebaseDatabase.getReference().child("user type").child(id);
    }

    public static void setValueToSharedPref(String value , Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_FILE_NAME , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(WHO_IS_LOGIN , value);
        editor.commit();
    }

    public static void saveEmploy(String value , Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_FILE_NAME , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SAVE_EMPLOY , value);
        editor.commit();
    }

    public static String getSaveEmploy(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_FILE_NAME , Context.MODE_PRIVATE);
        return sharedPreferences.getString(SAVE_EMPLOY , "NO_ONE");
    }

    public static void employLogout(Context context){
        setValueToSharedPref("NO_ONE" , context);
        saveEmploy("NO_ONE" , context);
    }

    public static String getSharedPrefFileValue(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_FILE_NAME , Context.MODE_PRIVATE);
        return sharedPreferences.getString(WHO_IS_LOGIN , "NO_ONE");
    }

    public static void adminLogout(Context context){
        setValueToSharedPref("NO_ONE" , context);
        firebaseAuth.signOut();
    }

    public static FirebaseAuth resetPassword(){
        return firebaseAuth;
    }

    public static void addNewFoodItem(String cardNo, String name, String dis, String price, Uri image, Context context) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();

        String imageId = UUID.randomUUID().toString();
        StorageReference temp = storageReference.child("images/" + imageId);

        temp.putFile(image).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //Snackbar.make(context.findViewById(android.R.id.content) , "image uploaded" , Snackbar.LENGTH_LONG).show();

                temp.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Uri downloadUrl = uri;
                        AdminNewItemModel data = new AdminNewItemModel(name, dis, price, imageId, uri.toString());
                        firebaseDatabase.getReference().child("food items").child(cardNo).child(name).setValue(data);
                    }
                });


                Toast.makeText(context, "image uploaded", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "failll imageeeee", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static void editFoodItem(String cardNo, String newName, String dis, String price, Uri image, Context context, String oldName, String OldImageName, String Olduri) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();
        if (image == null) {
            if (newName.equals(oldName)) {
                AdminNewItemModel data = new AdminNewItemModel(newName, dis, price, OldImageName, Olduri);
                firebaseDatabase.getReference().child("food items").child(cardNo).child(oldName).setValue(data);
            } else {
                firebaseDatabase.getReference().child("food items").child(cardNo).child(oldName).removeValue();

                AdminNewItemModel data = new AdminNewItemModel(newName, dis, price, OldImageName, Olduri);
                firebaseDatabase.getReference().child("food items").child(cardNo).child(newName).setValue(data);
            }
            return;
        }


        StorageReference temp = storageReference.child("images/" + OldImageName);

        temp.putFile(image).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //Snackbar.make(context.findViewById(android.R.id.content) , "image uploaded" , Snackbar.LENGTH_LONG).show();

                temp.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        if (newName.equals(oldName)) {
                            AdminNewItemModel data = new AdminNewItemModel(newName, dis, price, OldImageName, uri.toString());
                            firebaseDatabase.getReference().child("food items").child(cardNo).child(oldName).setValue(data);
                        } else {
                            firebaseDatabase.getReference().child("food items").child(cardNo).child(oldName).removeValue();

                            AdminNewItemModel data = new AdminNewItemModel(newName, dis, price, OldImageName, uri.toString());
                            firebaseDatabase.getReference().child("food items").child(cardNo).child(newName).setValue(data);
                        }
                    }
                });


                Toast.makeText(context, "image uploaded", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "failll imageeeee", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void placeOrder(String orderP , int priceP , Context context){
        DatabaseReference reference = firebaseDatabase.getReference().child("customers account").child(firebaseAuth.getCurrentUser().getUid());
       reference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               CustomerDetail_model temp2 = snapshot.getValue(CustomerDetail_model.class);
               if(context != null){
                   String key = firebaseDatabase.getReference().child("Online order queue").push().getKey();
                   PlaceOrderModel order = new PlaceOrderModel(
                           orderP,
                           priceP+"",

                           temp2.getName(),
                           temp2.getEmail(),
                           temp2.getAddress(),
                           temp2.getNumber(),
                           key,
                           temp2.getToken()
                   );
                   firebaseDatabase.getReference().child("Online order queue").child(key).setValue(order).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           if(task.isSuccessful()){
                               Toast.makeText(context, "order booked succecflluy", Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });
    }
}
