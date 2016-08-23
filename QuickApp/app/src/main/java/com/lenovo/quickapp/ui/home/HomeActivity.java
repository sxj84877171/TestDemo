package com.lenovo.quickapp.ui.home;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.lenovo.quickapp.App;
import com.lenovo.quickapp.R;
import com.lenovo.quickapp.base.BaseActivity;
import com.lenovo.quickapp.data.local.db.bean.Note;
import com.lenovo.quickapp.data.local.db.dao.DaoSession;

import org.greenrobot.greendao.rx.RxDao;
import org.greenrobot.greendao.rx.RxQuery;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class HomeActivity extends BaseActivity<HomePresent,HomeModel> implements HomeView {

    private RxDao<Note, Long> noteDao;
    private RxQuery<Note> notesQuery;

    @Bind(R.id.add_note)
    public Button addNote;

    @Bind(R.id.note_content)
    public EditText note_content;

    @Bind(R.id.query_notes)
    public Button query;

    @Bind(R.id.show)
    public ListView listView;

    private List<Note> notes;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        mPresenter.getNotes();
    }

    @OnClick(R.id.query_notes)
    public void query(View view) {
        mPresenter.getNotes();
    }

    @OnClick(R.id.add_note)
    public void onClick(View view) {
        String content = note_content.getText().toString();
        note_content.setText("");
        Note note = new Note();
        note.setComment(content);
        note.setText("not empty");
        mPresenter.addNote(note);
    }


    @OnClick(R.id.modify_note)
    public void modify(View view){

    }


    @Override
    public void showResult(List<Note> notes) {
        List<String> list = new ArrayList<>();
        for (Note note : notes) {
            list.add(note.getComment());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
    }

    @Override
    public void showSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showFail() {
        Toast.makeText(this, "fail", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgess() {

    }

    @Override
    public void stopProgess() {

    }
}
