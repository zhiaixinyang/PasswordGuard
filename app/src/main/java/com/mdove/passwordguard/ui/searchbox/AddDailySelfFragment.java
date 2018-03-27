package com.mdove.passwordguard.ui.searchbox;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.ui.searchbox.custom.IOnAddDailySelfClickListener;
import com.mdove.passwordguard.utils.KeyBoardUtils;

/**
 * Created by MDove on 2018/3/10.
 */

public class AddDailySelfFragment extends DialogFragment implements DialogInterface.OnKeyListener,
        ViewTreeObserver.OnPreDrawListener,
        View.OnClickListener {

    public static final String TAG = "SearchFragment";
    private TextView btnCancel;
    private EditText etSearchKeyword;
    private TextView btnAdd;

    private View view;

    public static AddDailySelfFragment newInstance() {
        Bundle bundle = new Bundle();
        AddDailySelfFragment searchFragment = new AddDailySelfFragment();
        searchFragment.setArguments(bundle);
        return searchFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.DialogStyle);
    }

    @Override
    public void onStart() {
        super.onStart();
        initDialog();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_add_daily_self, container, false);

        init();//实例化

        return view;
    }

    private void init() {
        btnCancel = (TextView) view.findViewById(R.id.btn_cancel);
        etSearchKeyword = (EditText) view.findViewById(R.id.et_search_keyword);
        btnAdd = (TextView) view.findViewById(R.id.btn_add);

        getDialog().setOnKeyListener(this);//键盘按键监听
        btnAdd.getViewTreeObserver().addOnPreDrawListener(this);//绘制监听
        //监听编辑框文字改变
        etSearchKeyword.addTextChangedListener(new TextWatcherImpl());
        //监听点击
        btnCancel.setOnClickListener(this);
        btnAdd.setOnClickListener(this);

        etSearchKeyword.postDelayed(new Runnable() {
            @Override
            public void run() {
                KeyBoardUtils.openKeyboard(getContext(), etSearchKeyword);
            }
        }, 300);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_cancel) {
            KeyBoardUtils.closeKeyboard(getContext(), etSearchKeyword);
            etSearchKeyword.setText("");
            dismiss();
        } else if (view.getId() == R.id.btn_add) {
            addDailySelf();
        }
    }

    /**
     * 初始化SearchFragment
     */
    private void initDialog() {
        Window window = getDialog().getWindow();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = (int) (metrics.widthPixels * 0.98); //DialogSearch的宽
        window.setLayout(width, WindowManager.LayoutParams.MATCH_PARENT);
        window.setGravity(Gravity.TOP);
        window.setWindowAnimations(R.style.DialogEmptyAnimation);//取消过渡动画 , 使DialogSearch的出现更加平滑
    }

    /**
     * 监听键盘按键
     */
    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            KeyBoardUtils.closeKeyboard(getContext(), etSearchKeyword);
        } else if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            addDailySelf();
        }
        return false;
    }

    /**
     * 监听搜索键绘制时
     */
    @Override
    public boolean onPreDraw() {
        btnAdd.getViewTreeObserver().removeOnPreDrawListener(this);
        return true;
    }

    /**
     * 监听编辑框文字改变
     */
    private class TextWatcherImpl implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    }

    private void addDailySelf() {
        String content = etSearchKeyword.getText().toString();
        if (TextUtils.isEmpty(content.trim())) {
            Toast.makeText(getContext(), "记点东西可好？", Toast.LENGTH_SHORT).show();
        } else {
            iOnSearchClickListener.OnAddDailySelfClick(content);//接口回调
            KeyBoardUtils.closeKeyboard(getContext(), etSearchKeyword);
            dismiss();
        }
    }

    private IOnAddDailySelfClickListener iOnSearchClickListener;

    public void setOnAddDailySelfClickListener(IOnAddDailySelfClickListener iOnSearchClickListener) {
        this.iOnSearchClickListener = iOnSearchClickListener;
    }

}
