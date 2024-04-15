using System;
using System.Collections.Generic;
using System.Linq;
using System.IO;
using Bytescout.Spreadsheet;
using System.Reflection.Metadata;

class Program
{
    // Imported "Bytescout.Spreadsheet NuGet package"

    // output in \converter\CSV_to_SQL\CSV_to_SQL\bin\Debug\net6.0\food_data.sql

    // if data input isn't being updated - try deleting food_data.xlsx file in:
    // \converter\CSV_to_SQL\CSV_to_SQL\bin\Debug\net6.0\food_data.xlsx

    const string INPUT_FILE = "running MTE.xlsx";
    const string OUTPUT_FILE = "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ.sql";
    const int MAX_ENTRIES = 10000;

    static void Main(string[] args)
    {
        if (File.Exists(OUTPUT_FILE))
            File.Delete(OUTPUT_FILE);

        Spreadsheet spreadsheet = new Spreadsheet();
        spreadsheet.LoadFromFile(INPUT_FILE);
        Worksheet worksheet = spreadsheet.Workbook.Worksheets[0];

        //ClearData(OUTPUT_FILE);
        AddActivities(OUTPUT_FILE, worksheet);
        AddPlans(OUTPUT_FILE, worksheet);
        AddPlansFromActivities(OUTPUT_FILE, worksheet);
        
        Console.WriteLine("Done!");
    }



    static void ClearData(string file)
    {
        using (StreamWriter writer = new StreamWriter(file, append: true))
        {
            string line = 
                "DELETE FROM meals_from_products;\n" +
                "DELETE FROM allergies_from_products;\n" +
                "DELETE FROM meal;\n" +
                "DELETE FROM product;\n" +
                "DELETE FROM allergy;";

            writer.WriteLine(line);
            Console.WriteLine("Cleared earlier data");
        }
    }

    static void AddActivities(string file, Worksheet worksheet)
    {
        using (StreamWriter writer = new StreamWriter(file, append: true))
        {
            int i;
            for (i = 1; i < MAX_ENTRIES; i++)
            {
                if (worksheet.Cell(i, 0).ToString() == "" ||
                    worksheet.Cell(i, 1).ToString() == "" ||
                    worksheet.Cell(i, 2).ToString() == "" ||
                    worksheet.Cell(i, 4).ToString() == "" ||
                    worksheet.Cell(i, 5).ToString() == "")
                    break;

                string id = worksheet.Cell(i, 0).Value.ToString();
                string activity_name = worksheet.Cell(i, 1).Value.ToString();
                string duration = worksheet.Cell(i, 2).Value.ToString();
                string activity_type = worksheet.Cell(i, 4).Value.ToString();
                string met = worksheet.Cell(i, 5).Value.ToString();

                string line = ("insert into PhysicalActivity (id_activity, " +
                    "activity_name, duration, activity_type, met) values " +
                    "(" + id + ", '" + activity_name + "', '" + duration + 
                    "', '" + activity_type + "', " + met + ");");

                writer.WriteLine(line);
            }
            Console.WriteLine("Added " + i.ToString() + " activities");
        }
    }

    static void AddPlans(string file, Worksheet worksheet)
    {
        using (StreamWriter writer = new StreamWriter(file, append: true))
        {
            int i;
            for (i = 1; i < MAX_ENTRIES; i++)
            {
                if (worksheet.Cell(i, 11).ToString() == "" ||
                    worksheet.Cell(i, 12).ToString() == "")
                    break;

                string id = worksheet.Cell(i, 11).Value.ToString();
                string plan_name = worksheet.Cell(i, 12).Value.ToString();

                string line = ("insert into Plan (id_plan, plan_name) values " +
                    "(" + id + ", '" + plan_name + "');");

                writer.WriteLine(line);
            }
            Console.WriteLine("Added " + i.ToString() + " plans");
        }
    }

    static void AddPlansFromActivities(string file, Worksheet worksheet)
    {
        using (StreamWriter writer = new StreamWriter(file, append: true))
        {
            int i;
            for (i = 1; i < MAX_ENTRIES; i++)
            {
                if (worksheet.Cell(i, 14).ToString() == "" ||
                    worksheet.Cell(i, 15).ToString() == "" ||
                    worksheet.Cell(i, 16).ToString() == "")
                    break;

                string id_plan = worksheet.Cell(i, 14).Value.ToString();
                string weekday = worksheet.Cell(i, 15).Value.ToString();
                string id_activity = worksheet.Cell(i, 16).Value.ToString();

                string line = ("insert into PlansFromActivities (id_plan, " +
                    "id_activity, weekday) values " + "(" + id_plan + ", " + id_activity + ", '" + weekday + "');");

                writer.WriteLine(line);
            }
            Console.WriteLine("Added " + i.ToString() + " relations");
        }
    }
}