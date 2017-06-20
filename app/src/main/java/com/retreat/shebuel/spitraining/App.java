package com.retreat.shebuel.spitraining;

import android.app.Application;

/**
 * Created by Shebuel on 14-06-2017 at 14:39.
 * Final Edits made on com.retreat.shebuel.spitraining
 */

public class App extends Application {
    private String empGlobalCode;

    public boolean isLanguageChanged() {
        return languageChanged;
    }

    public void setLanguageChanged(boolean languageChanged) {
        this.languageChanged = languageChanged;
    }

    private boolean languageChanged;

    public String getGlobalVariable() {
        return empGlobalCode;
    }

    public void setGlobalVariable(String globalVariable) {
        this.empGlobalCode = globalVariable;
    }
    @Override
    public void onCreate() {
        //reinitialize variable
    }
}
