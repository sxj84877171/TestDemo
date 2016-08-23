package com.lenovo.quickapp.ui.home;

import com.lenovo.quickapp.base.BasePresenter;
import com.lenovo.quickapp.data.local.db.bean.Note;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by elvissun on 8/22/2016.
 */
public class HomePresent extends BasePresenter<HomeModel, HomeView> {


    public void getNotes() {
        mView.showProgess();
        mRxManager.add(mModel.getNotexQuery().list().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<Note>>() {
            @Override
            public void call(List<Note> notes) {
                mView.stopProgess();
                mView.showResult(notes);
            }
        }));
    }


    public void addNote(Note note) {
        mView.showProgess();
        mRxManager.add(mModel.getNoteDao().insert(note).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Note>() {
            @Override
            public void call(Note note) {
                mView.stopProgess();
                mView.showSuccess();
            }
        }));
    }

    public void deleteNote(Note note) {
        mView.showProgess();
        mRxManager.add(mModel.getNoteDao().delete(note).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                mView.showSuccess();
            }
        }));
    }


    @Override
    public void onStart() {
        mModel.setContext(context);
        mRxManager.add(mModel.getNotexQuery().list().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<List<Note>>() {
            @Override
            public void call(List<Note> notes) {
                mView.showResult(notes);
            }
        }));
    }
}
