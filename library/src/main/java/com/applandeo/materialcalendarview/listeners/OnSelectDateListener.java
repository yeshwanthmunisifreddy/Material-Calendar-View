package com.applandeo.materialcalendarview.listeners;

import android.app.Dialog;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Mateusz Kornakiewicz on 27.07.2017.
 */

public interface OnSelectDateListener {
    void onSelect(List<Calendar> calendar, Dialog dialog);
}
