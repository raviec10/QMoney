
package com.crio.warmup.stock;

import com.crio.warmup.stock.dto.PortfolioTrade;
import com.crio.warmup.stock.dto.TiingoCandle;
import com.crio.warmup.stock.dto.TotalReturnsDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;
import com.crio.warmup.stock.dto.AnnualizedReturn;
import com.crio.warmup.stock.log.UncaughtExceptionHandler;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.crio.warmup.stock.portfolio.PortfolioManager;
import com.crio.warmup.stock.portfolio.PortfolioManagerFactory;


import com.crio.warmup.stock.dto.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
// import java.util.Comparator;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.logging.log4j.ThreadContext;
import com.crio.warmup.stock.dto.*;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.web.client.RestTemplate;


public class PortfolioManagerApplication {

  public static final String tiingoToken = "d6c585fdc09ce688edb1eb6eb610a13d99fbefb0";

  // TODO: CRIO_TASK_MODULE_JSON_PARSING
  //  Task:
  //       - Read the json file provided in the argument[0], The file is available in the classpath.
  //       - Go through all of the trades in the given file,
  //       - Prepare the list of all symbols a portfolio has.
  //       - if "trades.json" has trades like
  //         [{ "symbol": "MSFT"}, { "symbol": "AAPL"}, { "symbol": "GOOGL"}]
  //         Then you should return ["MSFT", "AAPL", "GOOGL"]
  //  Hints:
  //    1. Go through two functions provided - #resolveFileFromResources() and #getObjectMapper
  //       Check if they are of any help to you.
  //    2. Return the list of all symbols in the same order as provided in json.

  //  Note:
  //  1. There can be few unused imports, you will need to fix them to make the build pass.
  //  2. You can use "./gradlew build" to check if your code builds successfully.


  // public static List<String> mainReadFile(String[] args) throws IOException, URISyntaxException {

  //   File file = resolveFileFromResources(args[0]);
  //   byte[] byteArray = Files.readAllBytes(file.toPath());
  //   String content = new String(byteArray, "UTF8");

  //   ObjectMapper mapper = getObjectMapper();
  //   PortfolioTrade[] trades = mapper.readValue(content, PortfolioTrade[].class);

  //   List<String> symbols = new ArrayList<>();

  //   for (int i = 0; i < trades.length; i++) {
  //     symbols.add(trades[i].getSymbol());
  //   }

  //   return symbols;

  // }




  public static List<String> mainReadFile(String[] args) throws IOException, URISyntaxException {

    List<String> listOfSymbols = new ArrayList<>();
    List<PortfolioTrade> portfolioTrades = getObjectMapper()
        .readValue(resolveFileFromResources(args[0]), new TypeReference<List<PortfolioTrade>>() {});
    for (PortfolioTrade portfolioTrade : portfolioTrades) {
      listOfSymbols.add(portfolioTrade.getSymbol());
    }
    return listOfSymbols;

  //   // File file = resolveFileFromResources(args[0]);
  //   // ObjectMapper objectMapper = getObjectMapper();
  //   // PortfolioTrade [] trades = objectMapper.readValue(file,PortfolioTrade[].class);
  //   // List<String>symbols = new ArrayList<String>();
  //   // for(PortfolioTrade t : trades){

  //   //   symbols.add(t.getSymbol());
  //   // }
  //   //    return symbols;
  }


  // Note:
  // 1. You may need to copy relevant code from #mainReadQuotes to parse the Json.
  // 2. Remember to get the latest quotes from Tiingo API.






  // Note:
  // 1. You may have to register on Tiingo to get the api_token.
  // 2. Look at args parameter and the module instructions carefully.
  // 2. You can copy relevant code from #mainReadFile to parse the Json.
  // 3. Use RestTemplate#getForObject in order to call the API,
  //    and deserialize the results in List<Candle>



  private static void printJsonObject(Object object) throws IOException {
    Logger logger = Logger.getLogger(PortfolioManagerApplication.class.getCanonicalName());
    ObjectMapper mapper = new ObjectMapper();
    logger.info(mapper.writeValueAsString(object));
  }

  private static File resolveFileFromResources(String filename) throws URISyntaxException {
    return Paths.get(Thread.currentThread().getContextClassLoader().getResource(filename).toURI()).toFile();
  }

  private static ObjectMapper getObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper;
  }


  // TODO: CRIO_TASK_MODULE_JSON_PARSING
  //  Follow the instructions provided in the task documentation and fill up the correct values for
  //  the variables provided. First value is provided for your reference.
  //  A. Put a breakpoint on the first line inside mainReadFile() which says
  //    return Collections.emptyList();
  //  B. Then Debug the test #mainReadFile provided in PortfoliomanagerApplicationTest.java
  //  following the instructions to run the test.
  //  Once you are able to run the test, perform following tasks and record the output as a
  //  String in the function below.
  //  Use this link to see how to evaluate expressions -
  //  https://code.visualstudio.com/docs/editor/debugging#_data-inspection
  //  1. evaluate the value of "args[0]" and set the value
  //     to the variable named valueOfArgument0 (This is implemented for your reference.)
  //  2. In the same window, evaluate the value of expression below and set it
  //  to resultOfResolveFilePathArgs0
  //     expression ==> resolveFileFromResources(args[0])
  //  3. In the same window, evaluate the value of expression below and set it
  //  to toStringOfObjectMapper.
  //  You might see some garbage numbers in the output. Dont worry, its expected.
  //    expression ==> getObjectMapper().toString()
  //  4. Now Go to the debug window and open stack trace. Put the name of the function you see at
  //  second place from top to variable functionNameFromTestFileInStackTrace
  //  5. In the same window, you will see the line number of the function in the stack trace window.
  //  assign the same to lineNumberFromTestFileInStackTrace
  //  Once you are done with above, just run the corresponding test and
  //  make sure its working as expected. use below command to do the same.
  //  ./gradlew test --tests PortfolioManagerApplicationTest.testDebugValues

  public static List<String> debugOutputs() {

     String valueOfArgument0 = "trades.json";
     String resultOfResolveFilePathArgs0 = "/home/crio-user/workspace/raviec10-ME_QMONEY_V2/qmoney/bin/main/trades.json";
     String toStringOfObjectMapper = "com.fasterxml.jackson.databind.ObjectMapper@1573f9fc";
     String functionNameFromTestFileInStackTrace = "PortfolioManagerApplicationTest.mainReadFile()";
     String lineNumberFromTestFileInStackTrace = "29:1";


    return Arrays.asList(new String[]{valueOfArgument0, resultOfResolveFilePathArgs0,
        toStringOfObjectMapper, functionNameFromTestFileInStackTrace,
        lineNumberFromTestFileInStackTrace});
  }

  public static List<TotalReturnsDto>mainReadQuotesHelper(String[] args, List<PortfolioTrade>trades) throws IOException, URISyntaxException {

    RestTemplate restTemplate = new RestTemplate();
    List<TotalReturnsDto> tests = new ArrayList<TotalReturnsDto>();
    for(PortfolioTrade t : trades){
      String uri = prepareUrl(t, LocalDate.parse(args[1]), tiingoToken);
      // "https://www.tiingo.com/documentation/end-of-day" + t.getSymbol() + "/prices?startDate=" +t.getPurchaseDate().toString() + "&endDate=" + args[1] + "&token=d6c585fdc09ce688edb1eb6eb610a13d99fbefb0";
      TiingoCandle[] results = restTemplate.getForObject(uri, TiingoCandle[].class);
      if(results != null){
        tests.add(new TotalReturnsDto(t.getSymbol(), results[results.length-1].getClose()));
      }
    }
    return tests;
  }



  // public static List<String> mainReadQuotes(String[] args) throws IOException, URISyntaxException {
     
  //   File file = resolveFileFromResources(args[0]);
  //   // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  //   // LocalDate endDate = LocalDate.parse(args[1],formatter);
  //   LocalDate endDate = LocalDate.parse(args[1]);
  //   byte[] byteArray = Files.readAllBytes(file.toPath());
  //   String content = new String(byteArray, "UTF8");

  //   ObjectMapper mapper = getObjectMapper();
  //   PortfolioTrade[] portfolioTrades = mapper.readValue(content, PortfolioTrade[].class);

  //   String token = "d6c585fdc09ce688edb1eb6eb610a13d99fbefb0";
  //   String uri = "https://api.tiingo.com/tiingo/daily/$SYMBOL/prices?startDate=$STARTDATE&endDate=$ENDDATE&token=$APIKEY";

  //   List<TotalReturnsDto> totalReturnsDtoList = new ArrayList<>();

  //   for (PortfolioTrade portfolioTrade : portfolioTrades) {

  //     String url = uri.replace("$APIKEY", token).replace("$SYMBOL", portfolioTrade.getSymbol())
  //         .replace("$STARTDATE", portfolioTrade.getPurchaseDate().toString())
  //         .replace("$ENDDATE", endDate.toString());

  //     // TiingoCandle[] tiingoCandles = new
  //     // RestTemplate().getForObject(url,TiingoCandle[].class);

  //     RestTemplate restTemplate = new RestTemplate();
  //     String result = restTemplate.getForObject(url, String.class);
  //     TiingoCandle[] tiingoCandles = mapper.readValue(result, TiingoCandle[].class);

  //     // List<TiingoCandle> tiingoCandles = mapper.readValue(result,
  //     // ArrayList<TiingoCandle>());

  //     double sum = Stream.of(tiingoCandles)
  //         .filter(candle -> candle.getDate().equals(endDate) 
  //         || candle.getDate().equals(endDate.minusDays(1))).findFirst().get()
  //         .getClose();

  //     TotalReturnsDto totalReturn = new TotalReturnsDto(portfolioTrade.getSymbol(), sum);

  //     totalReturnsDtoList.add(totalReturn);

  //   }
  //   totalReturnsDtoList.sort(Comparator.comparing(TotalReturnsDto::getClosingPrice));
  //   List<String> symbols = new ArrayList<>();

  //   for (TotalReturnsDto a : totalReturnsDtoList) {
  //     symbols.add(a.getSymbol());
  //   }

  //   return symbols;

  // }



  public static List<String> mainReadQuotes(String[] args) throws IOException, URISyntaxException {
    // final String tiingoToken = "d6c585fdc09ce688edb1eb6eb610a13d99fbefb0";
    List<PortfolioTrade> portfolioTrades = readTradesFromJson(args[0]);
    LocalDate endDate = LocalDate.parse(args[1]);
    RestTemplate restTemplate = new RestTemplate();
    List<TotalReturnsDto> totalReturnsDtos = new ArrayList<>();
    List<String> listOfSortSymbolsOnClosingPrice = new ArrayList<>();
    for (PortfolioTrade portfolioTrade : portfolioTrades) {
      String tiingoURL = prepareUrl(portfolioTrade, endDate, tiingoToken);
      TiingoCandle[] tiingoCandleArray = restTemplate.getForObject(tiingoURL, TiingoCandle[].class);
      totalReturnsDtos.add(new TotalReturnsDto(portfolioTrade.getSymbol(),
          tiingoCandleArray[tiingoCandleArray.length - 1].getClose()));
    }
    Collections.sort(totalReturnsDtos,
        (a, b) -> Double.compare(a.getClosingPrice(), b.getClosingPrice()));
    for (TotalReturnsDto totalReturnsDto : totalReturnsDtos) {
      listOfSortSymbolsOnClosingPrice.add(totalReturnsDto.getSymbol());
    }
    return listOfSortSymbolsOnClosingPrice;
  }
  // Note:
  // Remember to confirm that you are getting same results for annualized returns as in Module 3.
  // public static List<String> mainReadQuotes(String[] args) throws IOException, URISyntaxException {
  //   final String TIINGO_API_TOKEN = 
  //   ObjectMapper objectMapper = getObjectMapper();
  //   List<PortfolioTrade>trades = Arrays.asList(objectMapper.readValue(resolveFileFromResources(args[0]), PortfolioTrade[].class));
    

  //   // List to store TotalReturnsDto for each stock symbol
  //   List<TotalReturnsDto> sortedByValue = mainReadQuotesHelper(args, trades);

  //   Collections.sort(sortedByValue, TotalReturnsDto.closingComparator);

  //   List<String> stocks = new ArrayList<String>();
  //   // Iterate through each PortfolioTrade
  //   for (TotalReturnsDto trd : sortedByValue) {

  //     stocks.add(trd.getSymbol());
  //   }
  //   return stocks;
  //       // Create URI for Tiingo API request
  // }     


  // public static final Comparator<TotalReturnsDto> closingComparator = new Comparator<TotalReturnsDto>() {
  //   public int compare(TotalReturnsDto t1, TotalReturnsDto t2) {
  //   return (int) t1.getClosingPrice().compareTo(t2.getClosingPrice());
  //  }
    
  // };
  
  // TODO:
  //  After refactor, make sure that the tests pass by using these two commands
  //  ./gradlew test --tests PortfolioManagerApplicationTest.readTradesFromJson
  //  ./gradlew test --tests PortfolioManagerApplicationTest.mainReadFile

  public static List<PortfolioTrade> readTradesFromJson(String filename) throws IOException, URISyntaxException {
    List<PortfolioTrade> portfolioTrades = getObjectMapper().readValue(
        resolveFileFromResources(filename), new TypeReference<List<PortfolioTrade>>() {});
    return portfolioTrades;
  }


  // public static List<PortfolioTrade> readTradesFromJson(String filename) throws IOException, URISyntaxException {
  //    return Collections.emptyList();
  // }

  

  // TODO:
  //  Build the Url using given parameters and use this function in your code to cann the API.


  public static String prepareUrl(PortfolioTrade trade, LocalDate endDate, String token) {
    return "https://api.tiingo.com/tiingo/daily/" + trade.getSymbol() + "/prices?startDate="
        + trade.getPurchaseDate() + "&endDate=" + endDate + "&token=" + token;
  }

//   public static String prepareUrl(PortfolioTrade trade, LocalDate endDate, String token) {
//     return UriComponentsBuilder
//             .fromUriString("https://api.tiingo.com/tiingo/daily/" + trade.getSymbol() + "/prices")
//             .queryParam("startDate", trade.getPurchaseDate())
//             .queryParam("endDate", endDate)
//             .queryParam("token", token)
//             .build()
//             .toUriString();
// }
  // TODO:
  //  Ensure all tests are passing using below command
  //  ./gradlew test --tests ModuleThreeRefactorTest

  public static String getToken() {
    return "dbafe4de20a19ef23df3deda52d513e373206cb0";
  }

  static Double getOpeningPriceOnStartDate(List<Candle> candles) {
    return candles.get(0).getOpen();
  }


  public static Double getClosingPriceOnEndDate(List<Candle> candles) {
    return candles.get(candles.size() - 1).getClose();
  }


  public static List<Candle> fetchCandles(PortfolioTrade trade, LocalDate endDate, String token) {
    RestTemplate restTemplate = new RestTemplate();
    String tiingoRestURL = prepareUrl(trade, endDate, token);
    TiingoCandle[] tiingoCandleArray =
        restTemplate.getForObject(tiingoRestURL, TiingoCandle[].class);
    return Arrays.stream(tiingoCandleArray).collect(Collectors.toList());
  }


  // public static List<AnnualizedReturn> mainCalculateSingleReturn(String[] args)
  //     throws IOException, URISyntaxException {

  //   File file = resolveFileFromResources(args[0]);
  //   LocalDate endDate = LocalDate.parse(args[1]);
  //   byte[] byteArray = Files.readAllBytes(file.toPath());
  //   String content = new String(byteArray, "UTF8");
  
  //   ObjectMapper mapper = getObjectMapper();
  //   PortfolioTrade[] portfolioTrades = mapper.readValue(content, PortfolioTrade[].class);
  
  //   String token = "d6c585fdc09ce688edb1eb6eb610a13d99fbefb0";
  //   String uri = "https://api.tiingo.com/tiingo/daily/$SYMBOL/prices?startDate=$STARTDATE&endDate=$ENDDATE&token=$APIKEY";

  //   List<AnnualizedReturn> annualReturn = new ArrayList<>();

  //   for (PortfolioTrade portfolioTrade : portfolioTrades) {
      
  //     String url = uri.replace("$APIKEY", token).replace("$SYMBOL", portfolioTrade.getSymbol())
  //         .replace("$STARTDATE", portfolioTrade.getPurchaseDate().toString())
  //         .replace("$ENDDATE", endDate.toString());

    
  //     RestTemplate restTemplate = new RestTemplate();
  //     String result = restTemplate.getForObject(url, String.class);
  //     TiingoCandle[] tiingoCandles = mapper.readValue(result, TiingoCandle[].class);

  //     double buyprice = Stream.of(tiingoCandles)
  //         .filter(candle -> candle.getDate().equals(portfolioTrade.getPurchaseDate()))
  //         .findFirst().get().getOpen();

  //     double sellprice = Stream.of(tiingoCandles)
  //         .filter(candle -> candle.getDate().equals(endDate))
  //         .findFirst().get().getClose();
        
  //     AnnualizedReturn turn = 
  //         calculateAnnualizedReturns(endDate,portfolioTrade,buyprice,sellprice);

  //     annualReturn.add(turn);

  //   }
    
  //   annualReturn.sort(Comparator.comparing(AnnualizedReturn::getAnnualizedReturn));
  //   Collections.reverse(annualReturn);

  //   return annualReturn;

  // }


  public static List<AnnualizedReturn> mainCalculateSingleReturn(String[] args)
      throws IOException, URISyntaxException {

    List<PortfolioTrade> portfolioTrades = readTradesFromJson(args[0]);
    List<AnnualizedReturn> annualizedReturns = new ArrayList<>();
    LocalDate localDate = LocalDate.parse(args[1]);
    for (PortfolioTrade portfolioTrade : portfolioTrades) {
      List<Candle> candles = fetchCandles(portfolioTrade, localDate, getToken());
      AnnualizedReturn annualizedReturn = calculateAnnualizedReturns(localDate, portfolioTrade,
          getOpeningPriceOnStartDate(candles), getClosingPriceOnEndDate(candles));
      annualizedReturns.add(annualizedReturn);
    }
    return annualizedReturns.stream()
        .sorted((a1, a2) -> Double.compare(a2.getAnnualizedReturn(), a1.getAnnualizedReturn()))
        .collect(Collectors.toList());
  }

  
  



  // static Double getOpeningPriceOnStartDate(List<Candle> candles) {
  //    return 0.0;
  // }


  // public static Double getClosingPriceOnEndDate(List<Candle> candles) {
  //    return 0.0;
  // }


  // public static List<Candle> fetchCandles(PortfolioTrade trade, LocalDate endDate, String token) {
  //    return Collections.emptyList();
  // }

  // public static List<AnnualizedReturn> mainCalculateSingleReturn(String[] args)
  //     throws IOException, URISyntaxException {
  //    return Collections.emptyList();
  // }

  // TODO: CRIO_TASK_MODULE_CALCULATIONS
  //  Return the populated list of AnnualizedReturn for all stocks.
  //  Annualized returns should be calculated in two steps:
  //   1. Calculate totalReturn = (sell_value - buy_value) / buy_value.
  //      1.1 Store the same as totalReturns
  //   2. Calculate extrapolated annualized returns by scaling the same in years span.
  //      The formula is:
  //      annualized_returns = (1 + total_returns) ^ (1 / total_num_years) - 1
  //      2.1 Store the same as annualized_returns
  //  Test the same using below specified command. The build should be successful.
  //     ./gradlew test --tests PortfolioManagerApplicationTest.testCalculateAnnualizedReturn





  public static AnnualizedReturn calculateAnnualizedReturns(LocalDate endDate, PortfolioTrade trade,
      Double buyPrice, Double sellPrice) {
    double total_num_years = ChronoUnit.DAYS.between(trade.getPurchaseDate(), endDate) / 365.2422;
    double totalReturns = (sellPrice - buyPrice) / buyPrice;
    double annualized_returns = Math.pow((1.0 + totalReturns), (1.0 / total_num_years)) - 1;
    return new AnnualizedReturn(trade.getSymbol(), annualized_returns, totalReturns);
  }

  // public static AnnualizedReturn calculateAnnualizedReturns(LocalDate endDate,
  //     PortfolioTrade trade, Double buyPrice, Double sellPrice) {
        
  //   double totalReturn = (sellPrice - buyPrice) / buyPrice;
  //   double totalnumdays = ChronoUnit.DAYS.between(trade.getPurchaseDate(),endDate);
  //   double totalnumyears = totalnumdays / 365;
  //   double inv = 1 / totalnumyears;

  //   double annualizedreturns = Math.pow((1 + totalReturn),inv) - 1;
    
  //   return new AnnualizedReturn(trade.getSymbol(),annualizedreturns, totalReturn);
  // }

//   public static AnnualizedReturn calculateAnnualizedReturns(LocalDate endDate, PortfolioTrade trade,
//   Double buyPrice, Double sellPrice) {
// double total_num_years = ChronoUnit.DAYS.between(trade.getPurchaseDate(), endDate) / 365.2422;
// double totalReturns = (sellPrice - buyPrice) / buyPrice;
// double annualized_returns = Math.pow((1.0 + totalReturns), (1.0 / total_num_years)) - 1;
// return new AnnualizedReturn(trade.getSymbol(), annualized_returns, totalReturns);
// }

  // public static AnnualizedReturn calculateAnnualizedReturns(LocalDate endDate,
  //     PortfolioTrade trade, Double buyPrice, Double sellPrice) {
  //     return new AnnualizedReturn("", 0.0, 0.0);
  // }

  
  // private static String readFileAsString(String fileName) throws IOException, URISyntaxException {
  //   return new String(Files.readAllBytes(resolveFileFromResources(fileName).toPath()), "UTF-8");
  // }

  private static String readFileAsString(String fileName) throws IOException, URISyntaxException {
    return new String(Files.readAllBytes(resolveFileFromResources(fileName).toPath()), "UTF-8");
  }

  public static List<AnnualizedReturn> mainCalculateReturnsAfterRefactor(String[] args)
      throws Exception {
    String file = args[0];
    LocalDate endDate = LocalDate.parse(args[1]);
    String contents = readFileAsString(file);
    ObjectMapper objectMapper = getObjectMapper();
    PortfolioManager portfolioManager = PortfolioManagerFactory.getPortfolioManager(new RestTemplate());
    List<PortfolioTrade> portfolioTrades =
        objectMapper.readValue(contents, new TypeReference<List<PortfolioTrade>>() {});
    return portfolioManager.calculateAnnualizedReturn(portfolioTrades, endDate);
  }


  // public static List<AnnualizedReturn> mainCalculateReturnsAfterNewServiceProvider(String[] args)
  //     throws Exception {
  //   String file = args[0];
  //   LocalDate endDate = LocalDate.parse(args[1]);
  //   String contents = readFileAsString(file);
  //   ObjectMapper objectMapper = getObjectMapper();
  //   PortfolioManager portfolioManager = PortfolioManagerFactory.getPortfolioManager("tingoo",new RestTemplate());
  //   List<PortfolioTrade> portfolioTrades =
  //       objectMapper.readValue(contents, new TypeReference<List<PortfolioTrade>>() {});
  //   // List<Candle> candles =
  //   // ((PortfolioManagerImpl)portfolioManager).getStockQuote(portfolioTrade.getSymbol(),
  //   // portfolioTrade.getPurchaseDate(), endDate);
  //   List<AnnualizedReturn> aa =
  //       portfolioManager.calculateAnnualizedReturnParallel(portfolioTrades, endDate,10);
  //   return aa;
  // }





















  public static void main(String[] args) throws Exception {
    Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler());
    ThreadContext.put("runId", UUID.randomUUID().toString());

    // printJsonObject(mainReadFile(args));

    // printJsonObject(mainReadQuotes(args));
  



    printJsonObject(mainCalculateSingleReturn(args));
    printJsonObject(mainCalculateSingleReturn(new String[] {"trades.json", "2020-01-01"}));
    printJsonObject(mainCalculateReturnsAfterRefactor(args));



  }
}

