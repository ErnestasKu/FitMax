package com.example.fitmax.ui.dashboard;

import android.graphics.Typeface;
import android.os.Bundle;
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
import com.example.fitmax.Database.PhysicalActivity;
import com.example.fitmax.Database.User;
import com.example.fitmax.Other.CommonMethods;
import com.example.fitmax.Other.SessionManager;
import com.example.fitmax.R;
import com.example.fitmax.databinding.FDashboardBinding;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DashboardFragment extends Fragment implements CalendarAdapter.OnItemListener {

    private com.example.fitmax.databinding.FDashboardBinding binding;
    private LocalDate selectedDate;
    public AppDatabase db = AppActivity.getDatabase();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // calendar --------------------------------------------------------------------------------
        selectedDate = LocalDate.now();
        setMonthView(selectedDate);

        // previous calendar
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backMonth();
            }
        });

        // next calendar
        binding.forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forwardMonth();
            }
        });

        // graph -----------------------------------------------------------------------------------
        createChart();


        return root;
    }

    private void createChart() {
        long id_user = SessionManager.getLoginSession(getContext());

        LocalDate startOfWeek = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        float totalCalories = 0;

        // get data
        ArrayList<BarEntry> list = new ArrayList<>();
        float weight = db.userDAO().getUser(id_user).getWeight();
        for (int i = 0; i < 7; i++) {

            int steps = db.completedStepsDAO().getUserSteps(id_user, startOfWeek.toString());
            List<PhysicalActivity> activities = db.completedActivitiesDAO().
                    getCompletedInDay(id_user, startOfWeek.toString());
            startOfWeek = startOfWeek.plusDays(1);

            float calories = 0;
            for (PhysicalActivity act : activities) {
                calories += CommonMethods.GetActivityCalories(act, weight);
            }
            calories += CommonMethods.GetStepCalories(steps, weight);
            list.add(new BarEntry(i, calories));
            totalCalories += calories;
        }

        // set total calories text -----------------------------------------------------------------
        binding.totalCalories.setText("Total calories burned this week: " + totalCalories + " kcal");

        // chart customization ---------------------------------------------------------------------
        BarChart barChart = binding.calorieChart;
        BarDataSet dataSet = new BarDataSet(list, "Calories burned this week");
        BarData data = new BarData(dataSet);
        barChart.setData(data);

        barChart.setDrawGridBackground(false);
        barChart.getDescription().setEnabled(false);
        barChart.setDrawBorders(false);
        barChart.setScaleEnabled(false);
        barChart.getLegend().setEnabled(false);

        // data format
        Typeface typeface = Typeface.create(Typeface.DEFAULT_BOLD, Typeface.BOLD);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setValueTextSize(16);
        dataSet.setValueTypeface(typeface);

        // x axis format
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        xAxis.setDrawGridLines(false);
        xAxis.setTypeface(typeface);
        xAxis.setTextSize(12);

        String[] xLabels = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xLabels));

        barChart.getAxisLeft().setTextSize(12);
        barChart.getAxisRight().setTextSize(12);
    }

    private void setMonthView(LocalDate date) {
        // moth - year display
        binding.monthYearText.setText(getMonthYearString(date));
        // day container
        ArrayList<String> days = getDayList(date);

        // instantiation
        CalendarAdapter adapter = new CalendarAdapter(days, this, selectedDate);
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
            if (i < firstDayOfWeek || i > daysInMonth + firstDayOfWeek - 1)
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

        // return if selected date is before creation date
        // or if it's after current day
        if (fullDate.isBefore(creationDate) || fullDate.isAfter(LocalDate.now()))
            return;

        // get activity info -----------------------------------------------------------------------
        long id_plan = db.planHistoryDAO().getUserPlan(id_user, fullDate.toString());

        int activityCount = db.plansFromActivitiesDAO().
                getActivityCountOfPlanDay(id_plan, fullDate.getDayOfWeek().name());

        int completedCount = db.completedActivitiesDAO().
                getCompletedCountInDay(id_user, fullDate.toString());

        // get step info ---------------------------------------------------------------------------
        int stepCount = db.stepHistoryDAO().getUserSteps(id_user, fullDate.toString());
        int completedSteps = db.completedStepsDAO().getUserSteps(id_user, fullDate.toString());

        // create view -----------------------------------------------------------------------------
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.calendar_popup, null);

        // set popup text --------------------------------------------------------------------------
        // header
        String header = fullDate.format(DateTimeFormatter.ofPattern("MMMM d"));
        ((TextView) view.findViewById(R.id.popup_date)).setText(header);

        String line_a = "Completed activities: " + completedCount + "/" + activityCount;
        String line_b = "Steps taken: " + completedSteps + "/" + stepCount;
        ((TextView) view.findViewById(R.id.activity_count_text)).setText(line_a);
        ((TextView) view.findViewById(R.id.step_count_text)).setText(line_b);

        // activity
        if (completedCount >= activityCount)
            view.findViewById(R.id.check_activity).setVisibility(view.VISIBLE);
        else
            view.findViewById(R.id.cross_activity).setVisibility(view.VISIBLE);

        if (completedSteps >= stepCount)
            view.findViewById(R.id.check_steps).setVisibility(view.VISIBLE);
        else
            view.findViewById(R.id.cross_steps).setVisibility(view.VISIBLE);

        // create popup  ---------------------------------------------------------------------------
        final PopupWindow popupWindow = new PopupWindow(
                view,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                true // Focusable
        );

        View clickedView = binding.calendarContainer.getLayoutManager().findViewByPosition(position);
        popupWindow.showAsDropDown(clickedView);

    }
}