package com.tourMaster.letsTour;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.tourMaster.letsTour.Service.TourDestService;
import com.tourMaster.letsTour.modals.DestinationImage;
import com.tourMaster.letsTour.modals.TourDestination;
import com.tourMaster.letsTour.modals.TourPackage;
import com.tourMaster.letsTour.modals.TravelAgency;
import io.netty.handler.codec.base64.Base64Encoder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


import java.io.*;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@SpringBootApplication
public class LetsTourApplication {

	public static void main(String[] args) {
		SpringApplication.run(LetsTourApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandRunner(TourDestService tourDestService)
	{
		return runner->{
			//getAllSpots(tourDestService);
			//saveDestinationWithAgency(tourDestService);
			//getASingleDestination(tourDestService);
			// create Tour Packages
			//createTourPackage(tourDestService);
			//deleteTourPackage(tourDestService);
			//efficientsearch(tourDestService);
			//callGetPlaceIdByName(tourDestService);
			//callGetUserReviewsByPlaceName(tourDestService);
			//callAvergaeRatingbypLaceName(tourDestService);
			//callGetDestinaationReviewDetails(tourDestService);
			//callGetDestinationReviewByPlaceName(tourDestService);
			//getAllPossibleAreaTypes(tourDestService);
			//convertImage();
			//uploadImageToImBB(tourDestService);
			practiseJackson();


		};
	}

	private void practiseJackson() {
		String rawJson= "";
		JsonMapper mapper = new JsonMapper();


	}

	private void uploadImageToImBB(TourDestService tourDestService) throws IOException
	{
		String apiKey="995f0410708339f893f18e0430738132";
		File folder = new File("/Users/aliabdullah/uploads/");
		File[] files = folder.listFiles((dir, name) ->
				name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png")|| name.toLowerCase().endsWith(".jpeg")
		);
       // Printing the file names
		if (files != null) {
			for (File file : files) {
				System.out.println("Image path: " + file.getAbsolutePath());
			}
		}
		// Uploading to ImgBB service
		for (File file: files)
		{

			String destinationId =file.toString().split("/")[file.toString().split("/").length-1].split("_")[0];
			String packageId=(file.toString().split("/")[file.toString().split("/").length-1].split("_")[1]).split("\\.")[0];
			System.out.println(destinationId);
			FileInputStream fis = new FileInputStream(file);
			byte [] byteArray= new byte[(int)file.length()];
			fis.read(byteArray);
			String base64Image = Base64.getEncoder().encodeToString(byteArray);
			System.out.println("Hello This is the base 64 encoded version of the image ");
			//System.out.println(base64Image);
			WebClient webClient = WebClient.builder().baseUrl("https://api.imgbb.com/1/upload").build();
			webClient.post().uri("?key="+apiKey)
					.body(BodyInserters.fromFormData("image",base64Image))
					.retrieve().bodyToMono(JsonNode.class).map(response->{
						System.out.println("====================IMAGE-URL==========================");
						String imageUrl=response.get("data").get("image").get("url").asText();
						System.out.println(imageUrl);

						//TourDestination tourDestination = tourDestService.getTourDestinationWithImages(Integer.parseInt(destinationId));
						//code for uploading the destination images
//						DestinationImage newDestinationImage= new DestinationImage(imageUrl,"Generic");
//						newDestinationImage.setTourDestination(tourDestination);
//						tourDestination.addDestinationImage(newDestinationImage);
				// code for uploading the destination package images
				  		List<TourPackage> tourPackages=tourDestService.getTourPackageByDestinationId(Integer.valueOf(destinationId));
						  List<TourPackage> filteredPackages = tourPackages.stream().filter(p->p.getId().equalsIgnoreCase(packageId)).collect(Collectors.toList());
						  filteredPackages.get(0).setPckgImg(imageUrl);
						tourDestService.updatePackage(filteredPackages.get(0));
						return 1;
					}).block();


		}

		/*ClassPathResource classPathResource =new ClassPathResource("TAJ4.jpg");
		InputStream is=classPathResource.getInputStream();
		byte [] byteArray= is.readAllBytes();
		String base64Image = Base64.getEncoder().encodeToString(byteArray);
		System.out.println("Hello This is the base 64 encoded version of the image ");
		//System.out.println(base64Image);
		WebClient webClient = WebClient.builder().baseUrl("https://api.imgbb.com/1/upload").build();
		webClient.post().uri("?key="+apiKey)
				.body(BodyInserters.fromFormData("image",base64Image))
				.retrieve().bodyToMono(JsonNode.class).map(response->{
					System.out.println("==============================================");
					System.out.println(response.get("status"));
                    return null;
                }).block();*/
	}

	private void convertImage() {
	}

	private void getAllPossibleAreaTypes(TourDestService tourDestService) {
		System.out.println(tourDestService.getAllPossibleAreaTypes());
	}

	private void callGetDestinationReviewByPlaceName(TourDestService tourDestService) {

	}


	private void callGetUserReviewsByPlaceName(TourDestService tourDestService) {
		tourDestService.getUserReviewsByPlaceName("Rohtas Fort")
				.subscribe(
						response-> System.out.println(response));
	}

	private void callGetPlaceIdByName(TourDestService tourDestService) {
		tourDestService.getPlaceIdByName("Rohtas Fort")
				.subscribe(response-> System.out.println(response));
	}


	private void efficientsearch(TourDestService tourDestService) {
		// defing the keyword
		String keyword="Fort";
		List<TourDestination> objectList=tourDestService.advanceSearchDestinations(keyword);
		System.out.println("=================================Following are the tour destinations");
		for (TourDestination x : objectList)
		{
			System.out.println(x);
		}
		System.out.println("=================================End of Advance Search");

		}

	private void deleteTourPackage(TourDestService tourDestService) {

		tourDestService.removePackageFromAgency(29);
		tourDestService.removePackageFromAgency(30);
		tourDestService.removePackageFromAgency(31);


	}

	private void createTourPackage(TourDestService tourDestService) {
		// creating the packages
		TourPackage p1= new TourPackage("Basic","Best for introverts singles and two friends", 3,4,true,true,25000,1);
		TourPackage p2= new TourPackage("Silver","Best for friends and small families", 4,5,true,true,35000,2);
		TourPackage p3= new TourPackage("Gold","A lifetime tour you can long for", 9,12,true,true,85000,3);
		TravelAgency ta =tourDestService.getAgencyWithPackages(1);
		TourDestination td=tourDestService.getDestinationWithPackages(4);
		td.addSingleTourPackage(p1);
		td.addSingleTourPackage(p2);
		td.addSingleTourPackage(p3);

		ta.addOnePackage(p1);
		ta.addOnePackage(p2);
		ta.addOnePackage(p3);

	tourDestService.updateAgencyWithPackage(ta);
	tourDestService.updateDestinationWithPackage(td);
		System.out.println("Agency updated with Pacakage");



	}

	private void getASingleDestination(TourDestService tourDestService) {
		TourDestination temp=tourDestService.getDestinationWithAgencies(4);
		//System.out.println(temp.getTavelAgencies());
	}

	private void saveDestinationWithAgency(TourDestService tourDestService) {
		Integer Id=3;
		TourDestination tourDestination= tourDestService.getDestinationWithAgencies(Id);
		System.out.println("Fetched Destination with the id"+Id+" is as follows");
		System.out.println(tourDestination);
		// gettingb the agencies
		TravelAgency t1=tourDestService.getTravelAgencyById(4);
		TravelAgency t2=tourDestService.getTravelAgencyById(5);
		TravelAgency t3=tourDestService.getTravelAgencyById(6);
		TravelAgency t4=tourDestService.getTravelAgencyById(7);
		TravelAgency t5=tourDestService.getTravelAgencyById(3);
		System.out.println("Following are the travel agencies got");
		System.out.println(t1);
		System.out.println(t2);
		System.out.println(t3);

		// setting the agencies;
//		tourDestination.addSingleTravelAgency(t1);
//		tourDestination.addSingleTravelAgency(t2);
//		tourDestination.addSingleTravelAgency(t3);
//		tourDestination.addSingleTravelAgency(t4);
//		tourDestination.addSingleTravelAgency(t5);
		// finally setting the travel destination

		tourDestService.saveDestinationWithAgencies(tourDestination);


	}

	private void getAllSpots(TourDestService tourDestService) {
		List<TourDestination> spotList =tourDestService.getAllTourDestinationPlaces();
		System.out.println(spotList);
	}


}
