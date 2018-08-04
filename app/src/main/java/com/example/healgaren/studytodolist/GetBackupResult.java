package com.example.healgaren.studytodolist;

import java.util.List;

public class GetBackupResult {
    private List<Memo> memos;

    public GetBackupResult(List<Memo> memos) {
        this.memos = memos;
    }

    public List<Memo> getMemos() {
        return memos;
    }

    public void setMemos(List<Memo> memos) {
        this.memos = memos;
    }
}
