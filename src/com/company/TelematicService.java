package com.company;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;


public class TelematicService {


    static void report(VehicleInfo vehicleInfo) {
        ObjectMapper mapper = new ObjectMapper();
        String json;
        try {
            File newJson = new File(vehicleInfo.getVIN() + ".json");
            json = mapper.writeValueAsString(vehicleInfo);
            FileWriter createFile = new FileWriter(newJson);
            createFile.write(json);
            createFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        double consumptionTotal = 0;
        double odometerTotal = 0;
        double engineSizeTotal = 0;
        double odometerReadingTotal = 0;
        int totalVehicles = 0;

        File file = new File(".");
        for (File f : file.listFiles()) {
            if (f.getName().endsWith(".json")) {
                FileReader jsonFiles;

                try {
                    jsonFiles = new FileReader(f);
                    VehicleInfo vi = mapper.readValue(jsonFiles, VehicleInfo.class);
                    consumptionTotal += vi.getConsumption();
                    odometerReadingTotal += vi.getOdometerReading();
                    odometerTotal += vi.getOdometer();
                    engineSizeTotal += vi.getEngineSize();
                    totalVehicles++;

                } catch (JsonMappingException e) {
                    e.printStackTrace();
                } catch (JsonParseException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }


        }


        double odometerAverage = Math.round((odometerTotal / totalVehicles) * 10.0) / 10.0;
        double consumptionAverage = Math.round((consumptionTotal / totalVehicles) * 10.0) / 10.0;
        double odometerReadingAverage = Math.round((odometerReadingTotal / totalVehicles) * 10.0) / 10.0;
        double engineSizeAverage = Math.round((engineSizeTotal / totalVehicles) * 10.0) / 10.0;


        String html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<title>Vehicle Telematics Dashboard</title>\n" +
                "<body>\n" +
                "<h1 align=\"center\">Averages for" + totalVehicles + "vehicles</h1>\n" +
                "<table align=\"center\">\n" +
                "    <tr>\n" +
                "        <th>Odometer (miles) |</th><th>Consumption (gallons) |</th><th>Last Oil Change |</th><th>Engine Size (liters)</th>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "        <td align=\"center\">" + odometerAverage + "</td><td align=\"center\">" + consumptionAverage + "</td><td align=\"center\">" + odometerReadingAverage + "</td align=\"center\"><td align=\"center\">" + engineSizeAverage + "</td>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "<h1 align=\"center\">History</h1>\n" +
                "<table align=\"center\" border=\"1\">\n" +
                "    <tr>\n" +
                "        <th>VIN</th><th>Odometer (miles)</th><th>Consumption (gallons)</th><th>Last Oil Change</th><th>Engine Size (liters)</th><th>MPG</th>\n" +
                "    </tr>";


        for (File f : file.listFiles()) {
            if (f.getName().endsWith(".json")) {
                FileReader jsonFiles;
                try {
                    jsonFiles = new FileReader(f);
                    VehicleInfo vi = mapper.readValue(jsonFiles, VehicleInfo.class);
                    html += "<tr>\n" +
                            "        <td align=\"center\">" + vi.getVIN() + "</td><td align=\"center\">" + vi.getOdometer() + "</td><td align=\"center\">" + vi.getConsumption() + "</td><td align=\"center\">" + vi.getOdometerReading() + "</td><td align=\"center\">" + vi.getEngineSize() + "</td align=\"center\"><td align=\"center\">" +  vi.getMilesPerGallon(vi.getOdometer(), vi.getConsumption()) + "</td>\n" +
                            "    </tr>";
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        html += "</table>\n" +
                "</body>\n" +
                "</html>";

        File index = new File("HTML.html");
        try {
            FileWriter newIndex = new FileWriter(index);
            newIndex.write(html);
            newIndex.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

