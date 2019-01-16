package com.htstudio.core.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;


import java.util.HashMap;
import java.util.List;


public abstract class BaseActivity extends AppCompatActivity {
    public enum PermissionResult {
        GRANTED,
        DENY,
        DENY_DONT_ASK_AGAIN
    }

    private class PermissionData {
        String permission;
        boolean isRequested = false;
        int requestCode;
        boolean isFirstDenied = false;
        boolean isDeniedSet =false;

        PermissionData(String permission, int requestCode) {
            this.permission = permission;
            this.requestCode = requestCode;
        }

        public PermissionData copy(){
            PermissionData newI = new PermissionData(this.permission, this.requestCode);
            newI.isRequested = this.isRequested;
            newI.isFirstDenied = this.isFirstDenied;
            newI.isDeniedSet = this.isDeniedSet;
            return newI;
        }
    }

    private HashMap<String, PermissionData> mPermissionResult = new HashMap<>();

    protected abstract String[] getPermissionNeed();

    protected abstract void onCreateActivity(@Nullable Bundle savedInstanceState);

    protected abstract void onPermissionResult(String permisson, PermissionResult result, boolean isRequested, boolean isFirstDeniedAskAgain);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPermission();
        onCreateActivity(savedInstanceState);

    }

    private void initPermission() {
        String[] pers = getPermissionNeed();
        if (pers.length == 0) return;
        for (int i = 0; i < pers.length; i++) {
            String per = pers[i];
            PermissionData data = new PermissionData(per, i);
            mPermissionResult.put(per, data);
        }
    }


    protected void requestPermission(String permission) {
        if (mPermissionResult.size() == 0 || !mPermissionResult.containsKey(permission)) {
            throw new RuntimeException("Permission not declare first, please put it in getPermissionNeed() method");
        } else {
            PermissionData data = mPermissionResult.get(permission);
            ActivityCompat.requestPermissions(this, new String[]{permission}, data.requestCode);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                for (String permis : mPermissionResult.keySet()) {
//                    PermissionData data = mPermissionResult.get(permis);
//                    if (checkPermission(permis)) {
//                        data.hasPermission = true;
//                        mPermissionResult.put(permis, data);
//                    }
//                }
//            }
//        }, 200);
    }

    protected boolean checkPermission(String per) {
        return ActivityCompat.checkSelfPermission(this, per) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (permissions.length == 1) {
            if (mPermissionResult.containsKey(permissions[0])) {
                if (grantResults.length > 0) {
                    PermissionData data = mPermissionResult.get(permissions[0]).copy();

                    PermissionResult result;
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        result = PermissionResult.GRANTED;
                    } else {
                        result = PermissionResult.DENY;
                        if (grantResults[0] == PackageManager.PERMISSION_DENIED && !ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[0])) {
                            result = PermissionResult.DENY_DONT_ASK_AGAIN;
                            if (data.isRequested && !data.isDeniedSet) {
                                data.isDeniedSet = true;
                                data.isFirstDenied = true;
                            }
                        }
                    }
                    onPermissionResult(permissions[0], result, data.isRequested, data.isFirstDenied);
                    data.isRequested = true;
                    data.isFirstDenied = false;
                    mPermissionResult.put(permissions[0], data);

                }
            }
        }

        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void startAppSetting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .setData(Uri.fromParts("package", getPackageName(), null));
        startActivity(intent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPermissionResult.clear();
    }
}
