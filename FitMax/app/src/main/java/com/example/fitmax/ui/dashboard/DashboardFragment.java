package com.example.fitmax.ui.dashboard;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitmax.CalendarAdapter;
import com.example.fitmax.Database.AppActivity;
import com.example.fitmax.Database.AppDatabase;
import com.example.fitmax.Database.User;
import com.example.fitmax.Other.SessionManager;
import com.example.fitmax.R;
import com.example.fitmax.databinding.FDashboardBinding;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class DashboardFragment extends Fragment implements CalendarAdapter.OnItemListener {

    private FDashboardBinding binding;
    private LocalDate selectedDate;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    private String currentMonth;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public AppDatabase db = AppActivity.getDatabase();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        selectedDate = LocalDate.now();
        setMonthView(selectedDate);


        binding.leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backMonth();
            }
        });

        binding.rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forwardMonth();
            }
        });
        return root;
    }

    private void setMonthView(LocalDate date) {
        // moth - year display
        binding.monthYearText.setText(getMonthYearString(date));
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        currentMonth = date.getYear() + "-" + date.getMonthValue() + "-";
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        // day container
        ArrayList<String> days = getDayList(date);

        // instantiation
        CalendarAdapter adapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 7);
        binding.calendarContainer.setLayoutManager(layoutManager);
        binding.calendarContainer.setAdapter(adapter);
    }

    private ArrayList<String> getDayList(LocalDate date) {

        ArrayList<String> days = new ArrayList<String>();
        YearMonth month = YearMonth.from(date);
        int daysInMonth = month.lengthOfMonth();
        LocalDate firstOfMonth = date.withDayOfMonth(1);
        int firstDayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i <= 42; i++)
            if (i < firstDayOfWeek || i > daysInMonth + firstDayOfWeek)
                days.add("");
            else
                days.add(String.valueOf(i - firstDayOfWeek + 1));
        return days;
    }

    private String getMonthYearString(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void backMonth() {
        if (selectedDate.getYear() > 2023) {
            selectedDate = selectedDate.minusMonths((1));
            setMonthView(selectedDate);
        }
    }

    public void forwardMonth() {
        selectedDate = selectedDate.plusMonths((1));
        setMonthView(selectedDate);
    }

    @Override
    public void onItemClick(int position, String dayText) {

        if (dayText.isEmpty())
            return;

        long id_user = SessionManager.getLoginSession(getContext());
        User user = db.userDAO().getUser(id_user);

        LocalDate fullDate = selectedDate.withDayOfMonth(Integer.parseInt(dayText));
        LocalDate creationDate = LocalDate.parse(user.getCreation_date());

        Log.d("EEEEEEEEE", "current day: " + LocalDate.now());
        Log.d("EEEEEEEEE", "selected day: " + fullDate);

        // return if selected date is before creation date
        // or if it's after current day
        if (fullDate.isBefore(creationDate) || fullDate.isAfter(LocalDate.now()))
            return;

        // get activity info -----------------------------------------------------------------------
        long id_plan = user.getId_plan();
        int activityCount = db.plansFromActivitiesDAO().getActivityCountOfPlanDay(id_plan, fullDate.getDayOfWeek().name());
        int completedCount = db.completedActivitiesDAO().getCompletedCountInDay(id_user, fullDate.toString());

        // create view -----------------------------------------------------------------------------
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.calendar_popup, null);

        // set popup text --------------------------------------------------------------------------
        String line_a = "Completed activities: " + completedCount + "/" + activityCount;
        String line_b = "No step count set";
        ((TextView) view.findViewById(R.id.activity_count_text)).setText(line_a);
        ((TextView) view.findViewById(R.id.step_count_text)).setText(line_a);

        // activity
        if (completedCount >= activityCount)
            view.findViewById(R.id.check_activity).setVisibility(view.VISIBLE);
        else
            view.findViewById(R.id.cross_activity).setVisibility(view.VISIBLE);

        // steps
        view.findViewById(R.id.check_steps).setVisibility(view.GONE);
        view.findViewById(R.id.cross_steps).setVisibility(view.GONE);
        view.findViewById(R.id.step_count_text).setVisibility(view.GONE);

        // create popup  ---------------------------------------------------------------------------
        final PopupWindow popupWindow = new PopupWindow(
                view,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true // Focusable
        );

        View clickedView = binding.calendarContainer.getLayoutManager().findViewByPosition(position);
        popupWindow.showAsDropDown(binding.monthYearText);

    }
}