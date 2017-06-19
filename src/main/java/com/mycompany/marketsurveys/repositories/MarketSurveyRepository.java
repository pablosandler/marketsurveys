package com.mycompany.marketsurveys.repositories;

import com.mycompany.marketsurveys.entities.MarketSurvey;
import com.mycompany.marketsurveys.enums.Sex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MarketSurveyRepository  extends JpaRepository<MarketSurvey, Long> {
    @Query("select ms from MarketSurvey ms where " +
            "ms.subject.code = :subjectCode and " +

            "ms.provider.id = :providerId and " +

            "ms.sex = :sex and " +
            "ms.country = :country and " +

            "ms.ageRange.from >= :ageFrom and " +
            "ms.ageRange.to <= :ageTo and " +

            "ms.incomeRange.from >= :incomeFrom and " +
            "ms.incomeRange.to <= :incomeTo and " +
            "ms.incomeRange.currency = :currency")
    List<MarketSurvey> find(
                            @Param("subjectCode") String subjectCode,
                            @Param("providerId") Long provider,
                            @Param("sex") Sex sex,
                            @Param("country") String country,
                            @Param("ageFrom") Long ageFrom,
                            @Param("ageTo") Long ageTo,
                            @Param("incomeFrom") Long incomeFrom,
                            @Param("incomeTo") Long incomeTo,
                            @Param("currency") String currency);

    /*
    //todo check extending Repository --> https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-part-two-crud/
    //todo check asycs --> https://www.petrikainulainen.net/programming/spring-framework/spring-data-jpa-tutorial-introduction-to-query-methods/

    @Query("select v from VerificationToken v where v.used=false and v.code=:code and v.verificationTokenType=:typ and v.validUntil>=:date")
    VerificationToken findValidToken(@Param("code") String token, @Param("typ") VerificationTokenType verificationTokenType, @Param("date")
     */
}
