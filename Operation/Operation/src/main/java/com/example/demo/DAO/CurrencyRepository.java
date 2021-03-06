package com.example.demo.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Entity.CurrencyJPA;

public interface CurrencyRepository extends JpaRepository<CurrencyJPA, Long> {
	
	List<CurrencyJPA> findAll();

	List<CurrencyJPA> findByChartName(String chartName);
	
	@Transactional
    @Modifying
    @Query(value = " INSERT INTO currency (CHARTNAME , SYMBOL , CODE , RATE , DESCRIPTION , RATEFLOAT , CREATEDATE , UPDATEDATE) "
    		     + " VALUES ( :#{#CJPA.chartName}, :#{#CJPA.symbol}, :#{#CJPA.code}, :#{#CJPA.rate}, :#{#CJPA.description}, :#{#CJPA.rateFloat}, :#{#CJPA.createDate}, :#{#CJPA.updateDate})", nativeQuery = true)
    int insertCurrencyTable(@Param("CJPA") CurrencyJPA student);
	
	@Transactional
    @Modifying
    @Query(value = " UPDATE currency "
    			 + " SET CHARTNAME = :#{#CJPA.chartName} , "
    			 + " SYMBOL = :#{#CJPA.symbol} , "
    			 + " CODE = :#{#CJPA.code} , "
    			 + " RATE = :#{#CJPA.rate} , "
    			 + " DESCRIPTION = :#{#CJPA.description} , "
    			 + " RATEFLOAT = :#{#CJPA.rateFloat} , "
    			 + " UPDATEDATE = :#{#CJPA.updateDate} ", nativeQuery = true)
    int updateCurrencyTable(@Param("CJPA") CurrencyJPA student);
	
	@Transactional
    @Modifying
    @Query(value = " DELETE FROM currency ", nativeQuery = true)
    int deleteCurrencyTable();
	
	@Transactional
    @Modifying
    @Query(value = " SELECT * FROM currency ", nativeQuery = true)
	List<CurrencyJPA> selectCurrencyTable();
}