package com.mdove.passwordguard.backup;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.utils.permission.PermissionGrantCallback;
import com.mdove.passwordguard.utils.permission.PermissionManager;
import com.mdove.passwordguard.utils.permission.PermissionUtils;

/**
 * Created by MDove on 2018/4/22.
 */

public class BackUpActivity extends BaseActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, BackUpActivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected boolean isNeedCustomLayout() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_up);

        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                if (!PermissionUtils.hasPermissions(BackUpActivity.this, permissions)) {
                    PermissionManager.askForPermission(BackUpActivity.this, 0, permissions, new PermissionGrantCallback() {
                        @Override
                        public void permissionGranted(int requestCode) {
                            BackUpService.start(BackUpActivity.this);
                        }

                        @Override
                        public void permissionRefused(int requestCode) {

                        }
                    });
                } else {
                    BackUpService.start(BackUpActivity.this);
                }
            }
        });

        findViewById(R.id.btn_restart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackUpService.restartBackUp(BackUpActivity.this);
            }
        });

        findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackUpService.deleteBackUpFile();
            }
        });
    }
}
