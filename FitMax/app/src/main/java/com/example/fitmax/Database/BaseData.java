package com.example.fitmax.Database;

public class BaseData {
    public static final String base_data =
            "insert into PhysicalActivity (id_activity, activity_name, duration, activity_type, met) values (1, 'Planks', '3 sets of 40 seconds', 'Core', 6);\n" +
            "insert into PhysicalActivity (id_activity, activity_name, duration, activity_type, met) values (2, 'Sit Ups', '3 sets of 15 reps', 'Core', 12);\n" +
            "insert into PhysicalActivity (id_activity, activity_name, duration, activity_type, met) values (3, 'Russian Twists', '4 sets of 9 reps', 'Core', 7.2);\n" +
            "insert into PhysicalActivity (id_activity, activity_name, duration, activity_type, met) values (4, 'Leg Raises', '4 sets of 10 reps', 'Core', 21.33);\n" +
            "insert into PhysicalActivity (id_activity, activity_name, duration, activity_type, met) values (5, 'Walking', '30 minutes', 'Holistic ', 120);\n" +
            "insert into PhysicalActivity (id_activity, activity_name, duration, activity_type, met) values (6, 'Racewalking', '25 minutes', 'Holistic ', 162.5);\n" +
            "insert into PhysicalActivity (id_activity, activity_name, duration, activity_type, met) values (7, 'Jogging', '20 minutes', 'Holistic ', 160);\n" +
            "insert into PhysicalActivity (id_activity, activity_name, duration, activity_type, met) values (8, 'Running', '15 minutes', 'Holistic ', 202.5);\n" +
            "insert into PhysicalActivity (id_activity, activity_name, duration, activity_type, met) values (9, 'Cycling Moderate', '30 minutes', 'Holistic ', 180);\n" +
            "insert into PhysicalActivity (id_activity, activity_name, duration, activity_type, met) values (10, 'Cycling Fast', '20 minutes', 'Holistic ', 240);\n" +
            "insert into PhysicalActivity (id_activity, activity_name, duration, activity_type, met) values (11, 'Yoga', '20 minutes', 'Holistic ', 50);\n" +
            "insert into PhysicalActivity (id_activity, activity_name, duration, activity_type, met) values (12, 'Squats', '3 sets of 10 reps', 'Lower', 8);\n" +
            "insert into PhysicalActivity (id_activity, activity_name, duration, activity_type, met) values (13, 'Deadlifts', '3 sets of  6 reps', 'Lower', 19.2);\n" +
            "insert into PhysicalActivity (id_activity, activity_name, duration, activity_type, met) values (14, 'Lunges', '3 sets of 10 reps', 'Lower', 8);\n" +
            "insert into PhysicalActivity (id_activity, activity_name, duration, activity_type, met) values (15, 'Leg Presses', '3 sets of 10 reps', 'Lower', 12);\n" +
            "insert into PhysicalActivity (id_activity, activity_name, duration, activity_type, met) values (16, 'Pull Ups', '3 sets of 6 reps', 'Upper', 10.8);\n" +
            "insert into PhysicalActivity (id_activity, activity_name, duration, activity_type, met) values (17, 'Push Ups', '3 sets of 15 reps', 'Upper', 12);\n" +
            "insert into PhysicalActivity (id_activity, activity_name, duration, activity_type, met) values (18, 'Bench Presses', '3 sets of 10 reps', 'Upper', 12);\n" +
            "insert into PhysicalActivity (id_activity, activity_name, duration, activity_type, met) values (19, 'Overhead Presses', '3 sets of 10 reps', 'Upper', 12);\n" +
            "insert into PhysicalActivity (id_activity, activity_name, duration, activity_type, met) values (20, 'Bicep Curls', '3 sets of 10 reps', 'Upper', 8);\n" +
            "insert into Plan (id_plan, plan_name) values (1, 'Weekends Off (moderate)');\n" +
            "insert into Plan (id_plan, plan_name) values (2, 'Weekends Off (light)');\n" +
            "insert into Plan (id_plan, plan_name) values (3, 'Daily');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (1, 18, 'Monday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (1, 19, 'Monday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (1, 20, 'Monday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (1, 8, 'Tuesday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (1, 3, 'Wednesday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (1, 4, 'Wednesday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (1, 1, 'Wednesday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (1, 2, 'Wednesday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (1, 10, 'Thursday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (1, 13, 'Friday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (1, 14, 'Friday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (1, 15, 'Friday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (1, 12, 'Friday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (2, 18, 'Monday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (2, 19, 'Monday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (2, 20, 'Monday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (2, 7, 'Tuesday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (2, 3, 'Wednesday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (2, 4, 'Wednesday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (2, 1, 'Wednesday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (2, 2, 'Wednesday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (2, 9, 'Thursday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (2, 13, 'Friday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (2, 14, 'Friday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (2, 15, 'Friday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (2, 12, 'Friday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (3, 18, 'Monday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (3, 19, 'Monday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (3, 20, 'Monday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (3, 8, 'Tuesday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (3, 3, 'Wednesday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (3, 4, 'Wednesday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (3, 1, 'Wednesday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (3, 2, 'Wednesday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (3, 10, 'Thursday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (3, 13, 'Friday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (3, 14, 'Friday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (3, 15, 'Friday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (3, 12, 'Friday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (3, 11, 'Saturday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (3, 9, 'Saturday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (3, 2, 'Saturday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (3, 6, 'Sunday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (3, 17, 'Sunday');\n" +
            "insert into PlansFromActivities (id_plan, id_activity, weekday) values (3, 2, 'Sunday');\n";
}
