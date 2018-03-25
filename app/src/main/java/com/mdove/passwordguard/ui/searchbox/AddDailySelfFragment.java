package com.mdove.passwordguard.ui.searchbox;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.ui.searchbox.adapter.SearchHistoryAdapter;
import com.mdove.passwordguard.ui.searchbox.custom.CircularRevealAnim;
import com.mdove.passwordguard.ui.searchbox.custom.IOnAddDailySelfClickListener;
import com.mdove.passwordguard.ui.searchbox.custom.IOnItemClickListener;
import com.mdove.passwordguard.ui.searchbox.custom.IOnSearchClickListener;
import com.mdove.passwordguard.ui.searchbox.db.SearchHistoryDB;
import com.mdove.passwordguard.utils.KeyBoardUtils;

import java.util.ArrayList;

/**
 * Created by MDove on 2018/3/10.
 */

public class AddDailySelfFragment extends DialogFragment implements DialogInterface.OnKeyListener,
        ViewTreeObserver.OnPreDrawListener,
        IOnItemClickListener, View.OnClickListener {

    public static final String TAG = "SearchFragment";
    private TextView ivSearchBack;
    private EditText etSearchKeyword;
    private TextView ivSearchSearch;

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
        ivSearchBack = (TextView) view.findViewById(R.id.iv_search_back);
        etSearchKeyword = (EditText) view.findViewById(R.id.et_search_keyword);
        ivSearchSearch = (TextView) view.findViewById(R.id.iv_search_search);

        getDialog().setOnKeyListener(this);//键盘按键监听
        ivSearchSearch.getViewTreeObserver().addOnPreDrawListener(this);//绘制监听
        //监听编辑框文字改变
        etSearchKeyword.addTextChangedListener(new TextWatcherImpl());
        //监听点击
        ivSearchBack.setOnClickListener(this);
        ivSearchSearch.setOnClickListener(this);

        etSearchKeyword.postDelayed(new Runnable() {
            @Override
            public void run() {
                KeyBoardUtils.openKeyboard(getContext(), etSearchKeyword);
            }
        }, 300);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_search_back || view.getId() == R.id.view_search_outside) {
            KeyBoardUtils.closeKeyboard(getContext(), etSearchKeyword);
            etSearchKeyword.setText("");
            dismiss();
        } else if (view.getId() == R.id.iv_search_search) {
            search();
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
            search();
        }
        return false;
    }

    /**
     * 监听搜索键绘制时
     */
    @Override
    public boolean onPreDraw() {
        ivSearchSearch.getViewTreeObserver().removeOnPreDrawListener(this);
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

    /**
     * 点击单个搜索记录
     */
    @Override
    public void onItemClick(String keyword) {
        iOnSearchClickListener.OnAddDailySelfClick(keyword);
        KeyBoardUtils.closeKeyboard(getContext(), etSearchKeyword);
    }

    /**
     * 删除单个搜索记录
     */
    @Override
    public void onItemDeleteClick(String keyword) {
    }

    private void search() {
        String searchKey = etSearchKeyword.getText().toString();
        if (TextUtils.isEmpty(searchKey.trim())) {
            Toast.makeText(getContext(), "记点东西可好？", Toast.LENGTH_SHORT).show();
        } else {
            iOnSearchClickListener.OnAddDailySelfClick(searchKey);//接口回调
            KeyBoardUtils.closeKeyboard(getContext(), etSearchKeyword);
        }
    }

    private IOnAddDailySelfClickListener iOnSearchClickListener;

    public void setOnAddDailySelfClickListener(IOnAddDailySelfClickListener iOnSearchClickListener) {
        this.iOnSearchClickListener = iOnSearchClickListener;
    }

}
