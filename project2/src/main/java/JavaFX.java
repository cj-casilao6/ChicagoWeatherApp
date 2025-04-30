import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;
import weather.Period;
import weather.WeatherAPI;
import javafx.geometry.Pos;
import javafx.scene.layout.Region;
import java.time.LocalDate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class JavaFX extends Application {
	// scene1 aspects
	private TextField temperature, currWeatherText, currDateText, credit, currPrecipitation, currWindSpeed, currWindDirection, forecastText, precedeDate, longForecastText;
	private Button threeDay;

	// scene2 day one
	private TextField threeWeatherText, threeCredit, dayOne, dayOneDayTemp, dayOneDayPrecip, dayOneNightTemp, dayOneNightPrecip, dayOneDayState, dayOneNightState;

	// scene2 day two
	private TextField dayTwo, dayTwoDayTemp, dayTwoDayPrecip, dayTwoNightTemp, dayTwoNightPrecip, dayTwoDayState, dayTwoNightState;

	// scene2 day three
	private TextField dayThree, dayThreeDayTemp, dayThreeDayPrecip, dayThreeNightTemp, dayThreeNightPrecip, dayThreeDayState, dayThreeNightState;

	private TextArea currForecast, dayOneForecast, dayTwoForecast, dayThreeForecast, longDetailedForecastText;
	private Button currWeather;
	private Scene scene1, scene2;

	public static void main(String[] args) {

		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception {
		ArrayList<Period> forecast = WeatherAPI.getForecast("LOT",77,70);
		if (forecast == null){
			throw new RuntimeException("Forecast did not load");
		}

		// Generate icon
		String iconURL = forecast.get(0).icon;
		ImageView weatherIcon;
		if (iconURL != null) {
			weatherIcon = new ImageView(new Image(iconURL));
			weatherIcon.setFitHeight(130);
			weatherIcon.setFitWidth(130);
		} else {
			System.out.println("Icon URL is null. Using placeholder.");
			weatherIcon = new ImageView(); // Placeholder for when the icon is unavailable
		}

		// Title of scene1 TextField
		primaryStage.setTitle("Chicago Weather Forecast");
		currWeatherText = new TextField("Current Weather in Chicago");
		currWeatherText.setEditable(false);
		currWeatherText.setStyle("-fx-font-size: 50px; -fx-font-weight: bold; -fx-background-color: #696969; -fx-font-family: 'Impact'; -fx-text-fill: white");
		currWeatherText.setAlignment(Pos.CENTER);

		// Text for different styling preceding Current date
		precedeDate = new TextField("   Today:");
		precedeDate.setEditable(false);
		precedeDate.setStyle("-fx-font-size: 32px; -fx-border-color: transparent; -fx-background-color: transparent; -fx-font-family: 'Impact';" + "-fx-font-weight: bold;");
		precedeDate.setPrefWidth(160);

		// Current date TextField
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EE, MMMM dd");
		currDateText = new TextField(today.format(formatter));
		currDateText.setEditable(false);
		currDateText.setStyle("-fx-font-size: 32px; -fx-border-color: transparent; -fx-background-color: transparent; -fx-font-family: 'Impact';");
		currDateText.setPrefWidth(255);

		// Temperature TextField
		temperature = new TextField( " " + String.valueOf(forecast.get(0).temperature) + "°F");
		temperature.setEditable(false);
		temperature.setStyle("-fx-font-size: 74px; -fx-border-color: transparent; -fx-background-color: transparent;" + "-fx-font-family: 'Impact'; ");
		temperature.setPrefWidth(280);

		// Precipitation TextField
		currPrecipitation = new TextField("Precipitation Chance: " + String.valueOf(forecast.get(0).probabilityOfPrecipitation.value) + "%");
		currPrecipitation.setEditable(false);
		currPrecipitation.setStyle("-fx-font-size: 25px; -fx-border-color: transparent; -fx-background-color: transparent;" + "-fx-font-family: 'Impact'; ");
		currPrecipitation.setPrefWidth(300);

		// Wind Speed TextField
		currWindSpeed = new TextField("Wind Speed: " + String.valueOf(forecast.get(0).windSpeed));
		currWindSpeed.setEditable(false);
		currWindSpeed.setStyle("-fx-font-size: 25px; -fx-border-color: transparent; -fx-background-color: transparent;" + "-fx-font-family: 'Impact'; ");
		currWindSpeed.setPrefWidth(350);

		// Wind Direction TextField
		currWindDirection = new TextField("Wind Direction: " + String.valueOf(forecast.get(0).windDirection));
		currWindDirection.setEditable(false);
		currWindDirection.setStyle("-fx-font-size: 25px; -fx-border-color: transparent; -fx-background-color: transparent;" + "-fx-font-family: 'Impact'; ");
		currWindDirection.setPrefWidth(350);

		// Text preceding detailed forecast TextField
		forecastText = new TextField("Short Description:");
		forecastText.setEditable(false);
		forecastText.setStyle("-fx-font-size: 25px; " + "-fx-border-color: transparent; " + "-fx-background-color: #DCDCDC; " + "-fx-font-weight: bold;" + "-fx-font-family: 'Impact'; ");
		forecastText.setPrefHeight(10);
		forecastText.setPrefWidth(350);

		// Actual detailed forecast TextField
		String detailedForecast = forecast.get(1).shortForecast;
		currForecast = new TextArea(detailedForecast);
		currForecast.setEditable(false);
		currForecast.setWrapText(true);
		currForecast.setStyle("-fx-font-size: 22px; " + "-fx-border-color: transparent; " + "-fx-font-family: 'Impact'; "+ "-fx-control-inner-background: F5F5F5;");
		currForecast.setPrefRowCount(3);

		// Text preceding detailed forecast TextField
		longForecastText = new TextField("Long Description:");
		longForecastText.setEditable(false);
		longForecastText.setStyle("-fx-font-size: 25px; " + "-fx-border-color: transparent; " + "-fx-background-color: #DCDCDC; " + "-fx-font-weight: bold;" + "-fx-font-family: 'Impact'; ");
		longForecastText.setPrefHeight(10);
		longForecastText.setPrefWidth(350);

		// Actual detailed forecast TextField
		String longDetailedForecast = forecast.get(1).detailedForecast;
		longDetailedForecastText = new TextArea(longDetailedForecast);
		longDetailedForecastText.setEditable(false);
		longDetailedForecastText.setWrapText(true);
		longDetailedForecastText.setStyle("-fx-font-size: 22px; " + "-fx-border-color: transparent; " + "-fx-font-family: 'Impact'; "+ "-fx-control-inner-background: F5F5F5;");
		longDetailedForecastText.setPrefRowCount(3);

 		// Credit to National Weather Service TextField
		credit = new TextField("Data provided by the National Weather Service");
		credit.setEditable(false);
		credit.setAlignment(Pos.CENTER);
		credit.setStyle("-fx-font-size: 21px; " + "-fx-border-color: transparent;" + "-fx-background-color: transparent;" + "-fx-font-family: 'Times New Roman';" + "-fx-font-style: italic;" + "-fx-font-weight: bold;");
		credit.setPrefWidth(435);
		credit.setPrefHeight(50);

		// 3-day forecast button to change scenes
		threeDay = new Button("3-Day Forecast");
		threeDay.setStyle("-fx-font-size: 21px; " + "-fx-background-color: #D3D3D3;" + "-fx-border-color: black; " + "-fx-border-width: 2px;" + "-fx-font-family: 'Impact';");
		threeDay.setPrefWidth(230);
		threeDay.setPrefHeight(50);
		threeDay.setOnAction(e -> {
			primaryStage.setScene(scene2);}
		);

		// Include spacer to give space between end of application and element
		Region spacer = new Region();
		spacer.setPrefWidth(40);

		HBox tempAndIcon = new HBox(spacer, weatherIcon, temperature);									// Icon and temperature
		HBox currDate = new HBox(precedeDate, currDateText);											// Date
		VBox dateTempIcon = new VBox(currDate, tempAndIcon);											// Date, Temperature, Icon
		HBox shortLongDesc = new HBox(forecastText, longForecastText);
		HBox shortLongDescText = new HBox(currForecast, longDetailedForecastText);
		VBox forecastDetail = new VBox(shortLongDesc, shortLongDescText);										// Forecast details
		VBox percipAndWind = new VBox(23, currPrecipitation, currWindSpeed, currWindDirection);		// Precipitation & Wind details
		HBox currBottom = new HBox(20, credit, threeDay);											// Credit & 3-day button
		HBox weatherDetails = new HBox(dateTempIcon, percipAndWind);									// Combine actual readings for the weather

		//
 		//		SECOND SCENE
 		//

		Region spacer2 = new Region();
		spacer2.setPrefWidth(20);

		// Title of scene2 TextField
		primaryStage.setTitle("Chicago Weather Forecast");
		threeWeatherText = new TextField("3-Day Forecast in Chicago");
		threeWeatherText.setEditable(false);
		threeWeatherText.setStyle("-fx-font-size: 35px; -fx-font-weight: bold; -fx-background-color: #696969; -fx-font-family: 'Impact'; -fx-text-fill: white");
		threeWeatherText.setAlignment(Pos.CENTER);
		threeWeatherText.setPrefHeight(18);

		// Credit to National Weather Service TextField
		threeCredit = new TextField("Data provided by the National Weather Service");
		threeCredit.setEditable(false);
		threeCredit.setAlignment(Pos.CENTER);
		threeCredit.setStyle("-fx-font-size: 21px; " + "-fx-border-color: transparent;" + "-fx-background-color: transparent;" + "-fx-font-family: 'Times New Roman';" + "-fx-font-style: italic;" + "-fx-font-weight: bold;");
		threeCredit.setPrefWidth(435);
		threeCredit.setPrefHeight(50);

		// Current weather button to go back to scene1
		currWeather = new Button("Current Weather");
		currWeather.setStyle("-fx-font-size: 21px; " + "-fx-background-color: #D3D3D3;" + "-fx-border-color: black; " + "-fx-border-width: 2px;" + "-fx-font-family: 'Impact';");
		currWeather.setPrefWidth(230);
		currWeather.setPrefHeight(50);
		currWeather.setOnAction(e -> {
			primaryStage.setScene(scene1);}
		);

		//
 		//		Day One Aspects
 		//
 		// Date TextField
		LocalDate day1 = today.plusDays(0);
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("EE, MMMM dd");
		dayOne = new TextField(day1.format(formatter1));
		dayOne.setStyle("-fx-font-size: 21px; -fx-border-color: transparent; -fx-background-color: transparent;" + "-fx-font-family: 'Impact'; ");
		dayOne.setAlignment(Pos.BASELINE_LEFT);
		dayOne.setPrefWidth(185);
		dayOne.setEditable(false);

		// Morning text
		dayOneDayState = new TextField("          Day Weather:");
		dayOneDayState.setEditable(false);
		dayOneDayState.setStyle("-fx-font-size: 15px; -fx-border-color: transparent; -fx-background-color: transparent;" + "-fx-font-family: 'Impact'; ");
		dayOneDayState.setPrefWidth(200);

		// Morning Icon
		String dayIcon1 = forecast.get(0).icon;
		ImageView dayOneDayIcon;
		if (dayIcon1 != null) {
			dayOneDayIcon = new ImageView(new Image(dayIcon1));
			dayOneDayIcon.setFitHeight(72);
			dayOneDayIcon.setFitWidth(72);
		} else {
			System.out.println("Icon URL is null. Using placeholder.");
			dayOneDayIcon = new ImageView(); // Placeholder for when the icon is unavailable
		}

		// Morning Temperature TextField
		dayOneDayTemp = new TextField( "     " + String.valueOf(forecast.get(0).temperature) + "°F");
		dayOneDayTemp.setEditable(false);
		dayOneDayTemp.setStyle("-fx-font-size: 30px; -fx-border-color: transparent; -fx-background-color: transparent;" + "-fx-font-family: 'Impact'; ");
		dayOneDayTemp.setPrefWidth(150);

		// Morning Precipitation TextField
		dayOneDayPrecip = new TextField("Precipitation Chance: " + String.valueOf(forecast.get(0).probabilityOfPrecipitation.value) + "%");
		dayOneDayPrecip.setEditable(false);
		dayOneDayPrecip.setStyle("-fx-font-size: 15px; -fx-border-color: transparent; -fx-background-color: transparent;" + "-fx-font-family: 'Impact'; ");
		dayOneDayPrecip.setPrefWidth(150);

		// Night Icon
		String nightIcon1 = forecast.get(1).icon;
		ImageView dayOneNightIcon;
		if (nightIcon1 != null) {
			dayOneNightIcon = new ImageView(new Image(nightIcon1));
			dayOneNightIcon.setFitHeight(72);
			dayOneNightIcon.setFitWidth(72);
		} else {
			System.out.println("Icon URL is null. Using placeholder.");
			dayOneNightIcon = new ImageView(); // Placeholder for when the icon is unavailable
		}

		// Night text
		dayOneNightState = new TextField("        Night Weather:");
		dayOneNightState.setEditable(false);
		dayOneNightState.setStyle("-fx-font-size: 15px; -fx-border-color: transparent; -fx-background-color: transparent;" + "-fx-font-family: 'Impact'; ");
		dayOneNightState.setPrefWidth(150);

		// Night Temperature TextField
		dayOneNightTemp = new TextField( "    " + String.valueOf(forecast.get(1).temperature) + "°F");
		dayOneNightTemp.setEditable(false);
		dayOneNightTemp.setStyle("-fx-font-size: 30px; -fx-border-color: transparent; -fx-background-color: transparent;" + "-fx-font-family: 'Impact'; ");
		dayOneNightTemp.setPrefWidth(150);

		// Night Precipitation TextField
		dayOneNightPrecip = new TextField("Precipitation Chance: " + String.valueOf(forecast.get(1).probabilityOfPrecipitation.value) + "%");
		dayOneNightPrecip.setEditable(false);
		dayOneNightPrecip.setStyle("-fx-font-size: 15px; -fx-border-color: transparent; -fx-background-color: transparent;" + "-fx-font-family: 'Impact'; ");
		dayOneNightPrecip.setPrefWidth(150);

		// Short forecast TextField
		String shortForecastOne = forecast.get(0).shortForecast;
		dayOneForecast = new TextArea(shortForecastOne);
		dayOneForecast.setEditable(false);
		dayOneForecast.setWrapText(true);
		dayOneForecast.setPrefWidth(175);
		dayOneForecast.setPrefHeight(55);
		dayOneForecast.setStyle("-fx-font-size: 18px; " + "-fx-border-color: transparent; " + "-fx-font-family: 'Impact'; "+ "-fx-control-inner-background: F5F5F5;");

		HBox threeBottom = new HBox(spacer2, threeCredit, currWeather);

		HBox iconAndDayTemp = new HBox(spacer2, dayOneDayIcon, dayOneDayTemp);
		VBox dayOneAM = new VBox(dayOneDayState, iconAndDayTemp, dayOneDayPrecip);
		HBox iconAndNightTemp = new HBox(spacer2, dayOneNightIcon, dayOneNightTemp);
		VBox dayOnePM = new VBox(dayOneNightState, iconAndNightTemp, dayOneNightPrecip);
		HBox fullDayOne = new HBox(dayOne, dayOneAM, dayOnePM, dayOneForecast);
		fullDayOne.setStyle("-fx-border-color: grey; -fx-border-width: 2px; -fx-border-radius: 5px;");

		//
 		//		DAY TWO ASPECTS
 		//
		// Date TextField
		LocalDate day2 = today.plusDays(1);
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("EE, MMMM dd");
		dayTwo = new TextField(day2.format(formatter2));
		dayTwo.setStyle("-fx-font-size: 21px; -fx-border-color: transparent; -fx-background-color: transparent;" + "-fx-font-family: 'Impact'; ");
		dayTwo.setAlignment(Pos.BASELINE_LEFT);
		dayTwo.setPrefWidth(185);
		dayTwo.setEditable(false);

		// Morning text
		dayTwoDayState = new TextField("          Day Weather:");
		dayTwoDayState.setEditable(false);
		dayTwoDayState.setStyle("-fx-font-size: 15px; -fx-border-color: transparent; -fx-background-color: transparent;" + "-fx-font-family: 'Impact'; ");
		dayTwoDayState.setPrefWidth(200);

		// Morning Icon
		String dayIcon2 = forecast.get(2).icon;
		ImageView dayTwoDayIcon;
		if (dayIcon2 != null) {
			dayTwoDayIcon = new ImageView(new Image(dayIcon2));
			dayTwoDayIcon.setFitHeight(72);
			dayTwoDayIcon.setFitWidth(72);
		} else {
			System.out.println("Icon URL is null. Using placeholder.");
			dayTwoDayIcon = new ImageView(); // Placeholder for when the icon is unavailable
		}

		// Morning Temperature TextField
		dayTwoDayTemp = new TextField( "     " + String.valueOf(forecast.get(2).temperature) + "°F");
		dayTwoDayTemp.setEditable(false);
		dayTwoDayTemp.setStyle("-fx-font-size: 30px; -fx-border-color: transparent; -fx-background-color: transparent;" + "-fx-font-family: 'Impact'; ");
		dayTwoDayTemp.setPrefWidth(150);

		// Morning Precipitation TextField
		dayTwoDayPrecip = new TextField("Precipitation Chance: " + String.valueOf(forecast.get(2).probabilityOfPrecipitation.value) + "%");
		dayTwoDayPrecip.setEditable(false);
		dayTwoDayPrecip.setStyle("-fx-font-size: 15px; -fx-border-color: transparent; -fx-background-color: transparent;" + "-fx-font-family: 'Impact'; ");
		dayTwoDayPrecip.setPrefWidth(150);

		// Night Icon
		String nightIcon2 = forecast.get(3).icon;
		ImageView dayTwoNightIcon;
		if (nightIcon2 != null) {
			dayTwoNightIcon = new ImageView(new Image(nightIcon2));
			dayTwoNightIcon.setFitHeight(72);
			dayTwoNightIcon.setFitWidth(72);
		} else {
			System.out.println("Icon URL is null. Using placeholder.");
			dayTwoNightIcon = new ImageView(); // Placeholder for when the icon is unavailable
		}

		// Night text
		dayTwoNightState = new TextField("        Night Weather:");
		dayTwoNightState.setEditable(false);
		dayTwoNightState.setStyle("-fx-font-size: 15px; -fx-border-color: transparent; -fx-background-color: transparent;" + "-fx-font-family: 'Impact'; ");
		dayTwoNightState.setPrefWidth(150);

		// Night Temperature TextField
		dayTwoNightTemp = new TextField( "    " + String.valueOf(forecast.get(3).temperature) + "°F");
		dayTwoNightTemp.setEditable(false);
		dayTwoNightTemp.setStyle("-fx-font-size: 30px; -fx-border-color: transparent; -fx-background-color: transparent;" + "-fx-font-family: 'Impact'; ");
		dayTwoNightTemp.setPrefWidth(150);

		// Night Precipitation TextField
		dayTwoNightPrecip = new TextField("Precipitation Chance: " + String.valueOf(forecast.get(3).probabilityOfPrecipitation.value) + "%");
		dayTwoNightPrecip.setEditable(false);
		dayTwoNightPrecip.setStyle("-fx-font-size: 15px; -fx-border-color: transparent; -fx-background-color: transparent;" + "-fx-font-family: 'Impact'; ");
		dayTwoNightPrecip.setPrefWidth(150);

		// Short forecast TextField
		String shortForecastTwo = forecast.get(2).shortForecast;
		dayTwoForecast = new TextArea(shortForecastOne);
		dayTwoForecast.setEditable(false);
		dayTwoForecast.setWrapText(true);
		dayTwoForecast.setPrefWidth(175);
		dayTwoForecast.setPrefHeight(55);
		dayTwoForecast.setStyle("-fx-font-size: 18px; " + "-fx-border-color: transparent; " + "-fx-font-family: 'Impact'; "+ "-fx-control-inner-background: F5F5F5;");

		HBox iconAndDayTempTwo = new HBox(spacer2, dayTwoDayIcon, dayTwoDayTemp);
		VBox dayTwoAM = new VBox(dayTwoDayState, iconAndDayTempTwo, dayTwoDayPrecip);
		HBox iconAndNightTempTwo = new HBox(spacer2, dayTwoNightIcon, dayTwoNightTemp);
		VBox dayTwoPM = new VBox(dayTwoNightState, iconAndNightTempTwo, dayTwoNightPrecip);
		HBox fullDayTwo = new HBox(dayTwo, dayTwoAM, dayTwoPM, dayTwoForecast);
		fullDayTwo.setStyle("-fx-border-color: grey; -fx-border-width: 2px; -fx-border-radius: 5px;");

		//
 		//		DAY THREE ASPECTS
 		//
		// Date TextField
		LocalDate day3 = today.plusDays(2);
		DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("EE, MMMM dd");
		dayThree = new TextField(day3.format(formatter3));
		dayThree.setStyle("-fx-font-size: 21px; -fx-border-color: transparent; -fx-background-color: transparent;" + "-fx-font-family: 'Impact'; ");
		dayThree.setAlignment(Pos.BASELINE_LEFT);
		dayThree.setPrefWidth(185);
		dayThree.setEditable(false);

		// Morning text
		dayThreeDayState = new TextField("          Day Weather:");
		dayThreeDayState.setEditable(false);
		dayThreeDayState.setStyle("-fx-font-size: 15px; -fx-border-color: transparent; -fx-background-color: transparent;" + "-fx-font-family: 'Impact'; ");
		dayThreeDayState.setPrefWidth(200);

		// Morning Icon
		String dayIcon3 = forecast.get(4).icon;
		ImageView dayThreeDayIcon;
		if (dayIcon3 != null) {
			dayThreeDayIcon = new ImageView(new Image(dayIcon3));
			dayThreeDayIcon.setFitHeight(72);
			dayThreeDayIcon.setFitWidth(72);
		} else {
			System.out.println("Icon URL is null. Using placeholder.");
			dayThreeDayIcon = new ImageView(); // Placeholder for when the icon is unavailable
		}

		// Morning Temperature TextField
		dayThreeDayTemp = new TextField( "     " + String.valueOf(forecast.get(4).temperature) + "°F");
		dayThreeDayTemp.setEditable(false);
		dayThreeDayTemp.setStyle("-fx-font-size: 30px; -fx-border-color: transparent; -fx-background-color: transparent;" + "-fx-font-family: 'Impact'; ");
		dayThreeDayTemp.setPrefWidth(150);

		// Morning Precipitation TextField
		dayThreeDayPrecip = new TextField("Precipitation Chance: " + String.valueOf(forecast.get(4).probabilityOfPrecipitation.value) + "%");
		dayThreeDayPrecip.setEditable(false);
		dayThreeDayPrecip.setStyle("-fx-font-size: 15px; -fx-border-color: transparent; -fx-background-color: transparent;" + "-fx-font-family: 'Impact'; ");
		dayThreeDayPrecip.setPrefWidth(150);

		// Night Icon
		String nightIcon3 = forecast.get(5).icon;
		ImageView dayThreeNightIcon;
		if (nightIcon3 != null) {
			dayThreeNightIcon = new ImageView(new Image(nightIcon3));
			dayThreeNightIcon.setFitHeight(72);
			dayThreeNightIcon.setFitWidth(72);
		} else {
			System.out.println("Icon URL is null. Using placeholder.");
			dayThreeNightIcon = new ImageView(); // Placeholder for when the icon is unavailable
		}

		// Night text
		dayThreeNightState = new TextField("        Night Weather:");
		dayThreeNightState.setEditable(false);
		dayThreeNightState.setStyle("-fx-font-size: 15px; -fx-border-color: transparent; -fx-background-color: transparent;" + "-fx-font-family: 'Impact'; ");
		dayThreeNightState.setPrefWidth(150);

		// Night Temperature TextField
		dayThreeNightTemp = new TextField( "    " + String.valueOf(forecast.get(5).temperature) + "°F");
		dayThreeNightTemp.setEditable(false);
		dayThreeNightTemp.setStyle("-fx-font-size: 30px; -fx-border-color: transparent; -fx-background-color: transparent;" + "-fx-font-family: 'Impact'; ");
		dayThreeNightTemp.setPrefWidth(150);

		// Night Precipitation TextField
		dayThreeNightPrecip = new TextField("Precipitation Chance: " + String.valueOf(forecast.get(5).probabilityOfPrecipitation.value) + "%");
		dayThreeNightPrecip.setEditable(false);
		dayThreeNightPrecip.setStyle("-fx-font-size: 15px; -fx-border-color: transparent; -fx-background-color: transparent;" + "-fx-font-family: 'Impact'; ");
		dayThreeNightPrecip.setPrefWidth(150);

		// Short forecast TextField
		String shortForecastThree = forecast.get(4).shortForecast;
		dayThreeForecast = new TextArea(shortForecastThree);
		dayThreeForecast.setEditable(false);
		dayThreeForecast.setWrapText(true);
		dayThreeForecast.setPrefWidth(175);
		dayThreeForecast.setPrefHeight(55);
		dayThreeForecast.setStyle("-fx-font-size: 18px; " + "-fx-border-color: transparent; " + "-fx-font-family: 'Impact'; "+ "-fx-control-inner-background: F5F5F5;");

		HBox iconAndDayTempThree = new HBox(spacer2, dayThreeDayIcon, dayThreeDayTemp);
		VBox dayThreeAM = new VBox(dayThreeDayState, iconAndDayTempThree, dayThreeDayPrecip);
		HBox iconAndNightTempThree = new HBox(spacer2, dayThreeNightIcon, dayThreeNightTemp);
		VBox dayThreePM = new VBox(dayThreeNightState, iconAndNightTempThree, dayThreeNightPrecip);
		HBox fullDayThree = new HBox(dayThree, dayThreeAM, dayThreePM, dayThreeForecast);
		fullDayThree.setStyle("-fx-border-color: grey; -fx-border-width: 2px; -fx-border-radius: 5px;");


		// Final setup
		VBox root = new VBox(13, currWeatherText, weatherDetails, forecastDetail, currBottom);		// Combine all elements to be top to bottom
		VBox.setMargin(currBottom, new javafx.geometry.Insets(0, 0, 10, 0));
		scene1 = new Scene(root, 700, 550);
		VBox root2 = new VBox(threeWeatherText, fullDayOne, fullDayTwo, fullDayThree ,threeBottom);		// Combine all elements of scene 2
		VBox.setMargin(threeBottom, new javafx.geometry.Insets(10, 0, 0, 0));			// Add gap between bottom row and weather info
		scene2 = new Scene(root2, 700, 550);
		primaryStage.setScene(scene1);
		primaryStage.show();
	}
}
