package com.example.kishan.myapplication;

public class DialogParameters {
    public String HeaderText;
    public String Message;
    public boolean IsSingleButton;
    public String PositiveButtonText;
    public String NegativeButtonText;

    public DialogParameters() {
        HeaderText = null;
        Message = null;
        IsSingleButton = false;
        PositiveButtonText = null;
        NegativeButtonText = null;
    }

    public void setHeaderText(String Text) {
        this.HeaderText = Text;
    }

    public void setMessage(String Text) {
        this.Message = Text;
    }

    public void setIsSingleButton(boolean value) {
        this.IsSingleButton = value;
    }

    public void setPositiveButtonText(String Text) {
        this.PositiveButtonText = Text;
    }

    public void setNegativeButtonText(String Text) {
        this.NegativeButtonText = Text;
    }

    public String getHeaderText() {
        return this.HeaderText;
    }

    public String getMessage() {
        return this.Message;
    }

    public boolean getIsSingleButton() {
        return this.IsSingleButton;
    }

    public String getPositiveButtonText() {
        return this.PositiveButtonText;
    }

    public String getNegativeButtonText() {
        return this.NegativeButtonText;
    }
}
