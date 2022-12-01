package br.com.pontes.jogodamemoria;

import android.text.TextPaint;
import android.text.style.UnderlineSpan;

import androidx.annotation.NonNull;

public class NoUnderLine extends UnderlineSpan {

    @Override
    public void updateDrawState(@NonNull TextPaint ds) {
        super.updateDrawState(ds);
        ds.setUnderlineText(false);
    }
}
