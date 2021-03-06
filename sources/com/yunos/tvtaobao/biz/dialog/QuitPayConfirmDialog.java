package com.yunos.tvtaobao.biz.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.yunos.tvtaobao.biz.dialog.util.SnapshotUtil;
import com.yunos.tvtaobao.businessview.R;
import com.yunos.tvtaobao.tvsdk.widget.focus.StaticFocusDrawable;
import com.zhiping.dev.android.logger.ZpLogger;
import java.lang.ref.WeakReference;

public class QuitPayConfirmDialog extends Dialog {
    /* access modifiers changed from: private */
    public String TAG = "QuitPayConfirmDialog";
    /* access modifiers changed from: private */
    public Activity mActivityContext;
    /* access modifiers changed from: private */
    public Bitmap mBitmap;
    /* access modifiers changed from: private */
    public DialogFocusPositionManager mOutermostLayout;
    private SnapshotUtil.OnFronstedGlassSreenDoneListener screenShotListener = new SnapshotUtil.OnFronstedGlassSreenDoneListener() {
        public void onFronstedGlassSreenDone(Bitmap bmp) {
            ZpLogger.v(QuitPayConfirmDialog.this.TAG, QuitPayConfirmDialog.this.TAG + ".onFronstedGlassSreenDone.bmp = " + bmp);
            Bitmap unused = QuitPayConfirmDialog.this.mBitmap = bmp;
            if (QuitPayConfirmDialog.this.mOutermostLayout == null) {
                return;
            }
            if (QuitPayConfirmDialog.this.mBitmap == null || QuitPayConfirmDialog.this.mBitmap.isRecycled()) {
                QuitPayConfirmDialog.this.mOutermostLayout.setBackgroundColor(QuitPayConfirmDialog.this.mActivityContext.getResources().getColor(R.color.ytbv_shadow_color_80));
                return;
            }
            QuitPayConfirmDialog.this.mOutermostLayout.setBackgroundDrawable(new LayerDrawable(new Drawable[]{new BitmapDrawable(QuitPayConfirmDialog.this.mBitmap), new ColorDrawable(QuitPayConfirmDialog.this.mActivityContext.getResources().getColor(R.color.ytbv_shadow_color_50))}));
        }
    };

    public QuitPayConfirmDialog(Context context, int theme) {
        super(context, theme);
    }

    public QuitPayConfirmDialog(Context context) {
        super(context);
    }

    public void show() {
        if (this.mOutermostLayout != null) {
            this.mOutermostLayout.setBackgroundDrawable((Drawable) null);
        }
        super.show();
        if (this.mActivityContext != null) {
            SnapshotUtil.getFronstedSreenShot(new WeakReference(this.mActivityContext), 5, 0.0f, this.screenShotListener);
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void dismiss() {
        super.dismiss();
        if (this.mOutermostLayout != null) {
            this.mOutermostLayout.setBackgroundDrawable((Drawable) null);
        }
        if (this.mBitmap != null && !this.mBitmap.isRecycled()) {
            this.mBitmap.recycle();
            this.mBitmap = null;
        }
    }

    public static class Builder {
        private boolean cancelable = true;
        private Context context;
        private String message;
        /* access modifiers changed from: private */
        public DialogInterface.OnClickListener negativeButtonClickListener;
        private String negativeButtonText;
        /* access modifiers changed from: private */
        public DialogInterface.OnClickListener positiveButtonClickListener;
        private String positiveButtonText;
        private String title;

        public Builder(Context context2) {
            this.context = context2;
        }

        public Builder setMessage(String message2) {
            this.message = message2;
            return this;
        }

        public Builder setTitle(String title2) {
            this.title = title2;
            return this;
        }

        public Builder setMessage(int message2) {
            this.message = (String) this.context.getText(message2);
            return this;
        }

        public Builder setCancelable(boolean cancelable2) {
            this.cancelable = cancelable2;
            return this;
        }

        public Builder setPositiveButton(int positiveButtonText2, DialogInterface.OnClickListener listener) {
            this.positiveButtonText = (String) this.context.getText(positiveButtonText2);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText2, DialogInterface.OnClickListener listener) {
            this.positiveButtonText = positiveButtonText2;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText2, DialogInterface.OnClickListener listener) {
            this.negativeButtonText = (String) this.context.getText(negativeButtonText2);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText2, DialogInterface.OnClickListener listener) {
            this.negativeButtonText = negativeButtonText2;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public QuitPayConfirmDialog show() {
            QuitPayConfirmDialog dialog = create();
            dialog.show();
            return dialog;
        }

        public QuitPayConfirmDialog create() {
            final QuitPayConfirmDialog dialog = new QuitPayConfirmDialog(this.context, R.style.ytbv_CustomDialog);
            dialog.setContentView(R.layout.dialog_confirmquitpay);
            DialogFocusPositionManager unused = dialog.mOutermostLayout = (DialogFocusPositionManager) dialog.findViewById(R.id.super_parent);
            dialog.mOutermostLayout.setSelector(new StaticFocusDrawable(this.context.getResources().getDrawable(R.drawable.ytbv_common_focus)));
            dialog.mOutermostLayout.initView();
            dialog.mOutermostLayout.requestFocus();
            if (this.context instanceof Activity) {
                Activity unused2 = dialog.mActivityContext = (Activity) this.context;
            }
            Button positiveButton = (Button) dialog.findViewById(R.id.positiveButton);
            if (this.positiveButtonText != null) {
                positiveButton.setVisibility(0);
                positiveButton.setText(this.positiveButtonText);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (Builder.this.positiveButtonClickListener != null) {
                            Builder.this.positiveButtonClickListener.onClick(dialog, -1);
                        }
                    }
                });
            } else {
                positiveButton.setVisibility(8);
            }
            Button negativeButton = (Button) dialog.findViewById(R.id.negativeButton);
            if (this.negativeButtonText != null) {
                negativeButton.setVisibility(0);
                negativeButton.setText(this.negativeButtonText);
                negativeButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (Builder.this.negativeButtonClickListener != null) {
                            Builder.this.negativeButtonClickListener.onClick(dialog, -2);
                        }
                        dialog.dismiss();
                    }
                });
            } else {
                negativeButton.setVisibility(8);
            }
            if (this.positiveButtonText == null && this.negativeButtonText == null) {
                dialog.findViewById(R.id.foot).setVisibility(8);
            }
            if (this.message != null) {
                ((TextView) dialog.findViewById(R.id.message)).setText(this.message);
            }
            if (this.title != null) {
                ((TextView) dialog.findViewById(R.id.title)).setText(this.title);
            }
            dialog.setCancelable(this.cancelable);
            positiveButton.requestFocus();
            return dialog;
        }
    }
}
