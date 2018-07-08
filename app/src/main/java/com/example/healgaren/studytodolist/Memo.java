package com.example.healgaren.studytodolist;

public class Memo {
    private String title;
    private String content;
    private String detailContent;

    public Memo(String title, String content) {
        this(title, content, "");
    }

    public Memo(String title, String content, String detailContent) {
        this.title = title;
        this.content = content;
        this.detailContent = detailContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDetailContent() {
        return detailContent;
    }

    public void setDetailContent(String detailContent) {
        this.detailContent = detailContent;
    }
}
