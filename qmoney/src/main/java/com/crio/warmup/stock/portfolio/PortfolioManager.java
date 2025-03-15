
package com.crio.warmup.stock.portfolio;

import com.crio.warmup.stock.dto.AnnualizedReturn;
import com.crio.warmup.stock.dto.PortfolioTrade;
import java.time.LocalDate;
import java.util.List;
import com.crio.warmup.stock.exception.StockQuoteServiceException;
public interface PortfolioManager {


  //CHECKSTYLE:OFF
  List<AnnualizedReturn> calculateAnnualizedReturnParallel(
    List<PortfolioTrade> portfolioTrades,
    LocalDate endDate, int numThreads) throws InterruptedException,
    StockQuoteServiceException;

//CHECKSTYLE:OFF


List<AnnualizedReturn> calculateAnnualizedReturn(List<PortfolioTrade> portfolioTrades,
    LocalDate endDate)
    throws StockQuoteServiceException;

 
}

