package com.kushagra.kushagra.test.model;

/**
 * Created by Prabin on 1/26/2019.
 */

public class HouseholdData {
    String hasToilet, hasNoToilet, toiletType, toiletWasteMgmt;
    String onlineServices;
    String primaryIncomeSource, ownsLand, ownsNoLand, landLocation, landOwnedBy, landUsedAs, landOnLease, landOnNoLease, landOnLeaseUsedAs;
    String primaryAgroProductions, lastYearAgroProductions, assetAnimals, assetBirds, assetsFishes, assetsBees, horticultureProductionCounts, sellsProductions, notSellsProductions, sellProductionList, sellProductionTo;
    String hasFruitPlants, hasNoFruitPlants, hasVegetableCrops, hasNoVegetableCrops;
    String lastYearIncome, lastYearExpense, worksAbroad, notWorkAbroad, remittanceSpentOn, lastYearInvestments, productionsSustainableFor, incomeIsSufficient, incomeIsNotSufficient, additionalCashSource, hasTakenLoan, hasNotTakenLoan, loanSource, takenLoanFor, durationToClearLoan;

    String knowsSamhit, notKnowSamhit, builtAsPerSamhit, notBuiltAsPerSamhit, notKnowBuiltAsPerSamhit, hasPermissionBlueprint, hasNoPermissionBlueprint, hasSafeZoneNearHouse, hasNoSafeZoneNearHouse, houseSusceptToCalamity, houseNoSusceptToCalamity, infoAboutEarthquake, noInfoAboutEarthquake, knowSafeZoneInHome, knowsNoSafeZoneInHome, hasSuppliesForSOE, hasNoSuppliesForSOE;
    String affectedByCalamitiesList, lastMajorDisaster, hasAssetInsurance, hasNoAssetInsurance, assetsListWithInsurance, stepsTakenToMinimizeDisasterEffects;
    String isViolenceVictim, isNotViolenceVictim, deathCount, injuryCount, missingCount, assetLossAmountRs, isSued, isNotSued, isShifted, isNotShifted, isSexuallyAbuse, isNotSexuallyAbused, memberMissing, memberNotMissing, feelsSafeInMunicipal, feelsNoSafeInMunicipal, reasonFeelsSafe, reasonNoFeelSafe, wardNoWhereNotFeelSafe, regionNameWhereNotFeelSafe;

    String homeMemberUptoAge16Works, noHomeMemberUptoAge16Works, uptoAge16WoringBoyCount, uptoAge16WorkingGirlCount, memberUptoAge16HiredWork, noMemberUptoAge16HiredWork, uptoAge16HiredBoyCount, uptoAge16HiredGirlCount, childrenIsOnBadInfluence, childrenNotOnBadInfluence, childrenBadInfluenceOn;
    String wayOfMakingFamilyDecision, assetsOnFemaleName;
    String allowanceListTakenByFamily, memberHasInvolvedOnDevelopment, memberHasNotInvolvedOnDevelopment, opinionOnDevelopmentPriority;


    // Edit text - Agriculture
    String annabali_muri, dhan_muri, makai_muri, kodo_muri, ghau_muri, fapar_muri, others_muri;
    String oil_kg, daal_kg, tarkari_kg, freshTarkari_kg, aalu_kg, masala_kg, falful_kg, kandamul_kg, others_kg;

    String cow_sthaniya, cow_unnat, buffalo_sthaniya, buffalo_unnat, goat_sthaniya, goat_unnat, sheep_sthaniya, sheep_unnat, pig_sthaniya, pig_unnat, other_animal;
    String hen_sthaniya, hen_unnat, pigeon_count, other_birds;

    String fish_count, beehive_count, other_pasupanchi;

    String milkCurd_litre, ghee_kg, otherDairy_kg, meat_kg, compost_quintal, urine_litre, wool_kg, egg_crate, fish_kg, honey_kg, other_production_kg;

    // Edit text - Income Expense
    String incomeAgriculture, incomeBusiness, incomeSalaryPension, incomeSocialAllowance, incomeForeignEmp, incomeWages, incomeRent, incomeInterestInvest, incomeOthers;
    String expenseFood, expenseCloth, expenseEducation, expenseHealth, expenseEntertain, expenseRent, expenseAgriculture, expenseInstallment, expenseFuel, expenseTranport, expenseOthers;

    // Edit text - Disaster / Violence
//    String deathCount, injuredCount, missingCount, assetLossCount;
//    String unsafeWardNo, unsafeRegion;

    // Edit text - Social
//    String workingBoyCount, workingGirlCount, hiredBoyCount, hiredGirlCount;


    public HouseholdData() {
        this.hasToilet = "0";
        this.hasNoToilet = "0";
        this.toiletType = "0";
        this.toiletWasteMgmt = "0";
        this.onlineServices = "0";
        this.primaryIncomeSource = "0";
        this.ownsLand = "0";
        this.ownsNoLand = "0";
        this.landLocation = "0";
        this.landOwnedBy = "0";
        this.landUsedAs = "0";
        this.landOnLease = "0";
        this.landOnNoLease = "0";
        this.landOnLeaseUsedAs = "0";
        this.primaryAgroProductions = "0";
        this.lastYearAgroProductions = "0";
        this.assetAnimals = "0";
        this.assetBirds = "0";
        this.assetsFishes = "0";
        this.assetsBees = "0";
        this.horticultureProductionCounts = "0";
        this.sellsProductions = "0";
        this.notSellsProductions = "0";
        this.sellProductionList = "0";
        this.sellProductionTo = "0";
        this.hasFruitPlants = "0";
        this.hasNoFruitPlants = "0";
        this.hasVegetableCrops = "0";
        this.hasNoVegetableCrops = "0";
        this.lastYearIncome = "0";
        this.lastYearExpense = "0";
        this.worksAbroad = "0";
        this.notWorkAbroad = "0";
        this.remittanceSpentOn = "0";
        this.lastYearInvestments = "0";
        this.productionsSustainableFor = "0";
        this.incomeIsSufficient = "0";
        this.incomeIsNotSufficient = "0";
        this.additionalCashSource = "0";
        this.hasTakenLoan = "0";
        this.hasNotTakenLoan = "0";
        this.loanSource = "0";
        this.takenLoanFor = "0";
        this.durationToClearLoan = "0";
        this.knowsSamhit = "0";
        this.notKnowSamhit = "0";
        this.builtAsPerSamhit = "0";
        this.notBuiltAsPerSamhit = "0";
        this.notKnowBuiltAsPerSamhit = "0";
        this.hasPermissionBlueprint = "0";
        this.hasNoPermissionBlueprint = "0";
        this.hasSafeZoneNearHouse = "0";
        this.hasNoSafeZoneNearHouse = "0";
        this.houseSusceptToCalamity = "0";
        this.houseNoSusceptToCalamity = "0";
        this.infoAboutEarthquake = "0";
        this.noInfoAboutEarthquake = "0";
        this.knowSafeZoneInHome = "0";
        this.knowsNoSafeZoneInHome = "0";
        this.hasSuppliesForSOE = "0";
        this.hasNoSuppliesForSOE = "0";
        this.affectedByCalamitiesList = "0";
        this.lastMajorDisaster = "0";
        this.hasAssetInsurance = "0";
        this.hasNoAssetInsurance = "0";
        this.assetsListWithInsurance = "0";
        this.stepsTakenToMinimizeDisasterEffects = "0";
        this.isViolenceVictim = "0";
        this.isNotViolenceVictim = "0";
        this.deathCount = "0";
        this.injuryCount = "0";
        this.missingCount = "0";
        this.assetLossAmountRs = "0";
        this.isSued = "0";
        this.isNotSued = "0";
        this.isShifted = "0";
        this.isNotShifted = "0";
        this.isSexuallyAbuse = "0";
        this.isNotSexuallyAbused = "0";
        this.memberMissing = "0";
        this.memberNotMissing = "0";
        this.feelsSafeInMunicipal = "0";
        this.feelsNoSafeInMunicipal = "0";
        this.reasonFeelsSafe = "0";
        this.reasonNoFeelSafe = "0";
        this.wardNoWhereNotFeelSafe = "0";
        this.regionNameWhereNotFeelSafe = "0";
        this.homeMemberUptoAge16Works = "0";
        this.noHomeMemberUptoAge16Works = "0";
        this.uptoAge16WoringBoyCount = "0";
        this.uptoAge16WorkingGirlCount = "0";
        this.memberUptoAge16HiredWork = "0";
        this.noMemberUptoAge16HiredWork = "0";
        this.uptoAge16HiredBoyCount = "0";
        this.uptoAge16HiredGirlCount = "0";
        this.childrenIsOnBadInfluence = "0";
        this.childrenNotOnBadInfluence = "0";
        this.childrenBadInfluenceOn = "0";
        this.wayOfMakingFamilyDecision = "0";
        this.assetsOnFemaleName = "0";
        this.allowanceListTakenByFamily = "0";
        this.memberHasInvolvedOnDevelopment = "0";
        this.memberHasNotInvolvedOnDevelopment = "0";
        this.opinionOnDevelopmentPriority = "0";
        this.annabali_muri = "0";
        this.dhan_muri = "0";
        this.makai_muri = "0";
        this.kodo_muri = "0";
        this.ghau_muri = "0";
        this.fapar_muri = "0";
        this.others_muri = "0";
        this.oil_kg = "0";
        this.daal_kg = "0";
        this.tarkari_kg = "0";
        this.freshTarkari_kg = "0";
        this.aalu_kg = "0";
        this.masala_kg = "0";
        this.falful_kg = "0";
        this.kandamul_kg = "0";
        this.others_kg = "0";
        this.cow_sthaniya = "0";
        this.cow_unnat = "0";
        this.buffalo_sthaniya = "0";
        this.buffalo_unnat = "0";
        this.goat_sthaniya = "0";
        this.goat_unnat = "0";
        this.sheep_sthaniya = "0";
        this.sheep_unnat = "0";
        this.pig_sthaniya = "0";
        this.pig_unnat = "0";
        this.other_animal = "0";
        this.hen_sthaniya = "0";
        this.hen_unnat = "0";
        this.pigeon_count = "0";
        this.other_birds = "0";
        this.fish_count = "0";
        this.beehive_count = "0";
        this.other_pasupanchi = "0";
        this.milkCurd_litre = "0";
        this.ghee_kg = "0";
        this.otherDairy_kg = "0";
        this.meat_kg = "0";
        this.compost_quintal = "0";
        this.urine_litre = "0";
        this.wool_kg = "0";
        this.egg_crate = "0";
        this.fish_kg = "0";
        this.honey_kg = "0";
        this.other_production_kg = "0";
        this.incomeAgriculture = "0";
        this.incomeBusiness = "0";
        this.incomeSalaryPension = "0";
        this.incomeSocialAllowance = "0";
        this.incomeForeignEmp = "0";
        this.incomeWages = "0";
        this.incomeRent = "0";
        this.incomeInterestInvest = "0";
        this.incomeOthers = "0";
        this.expenseFood = "0";
        this.expenseCloth = "0";
        this.expenseEducation = "0";
        this.expenseHealth = "0";
        this.expenseEntertain = "0";
        this.expenseRent = "0";
        this.expenseAgriculture = "0";
        this.expenseInstallment = "0";
        this.expenseFuel = "0";
        this.expenseTranport = "0";
        this.expenseOthers = "0";
    }

    public String getAll() {
        return "hasToilet " + this.hasToilet + "\n" +
                "hasNoToilet " + this.hasNoToilet + "\n" +
                "toiletType " + this.toiletType + "\n" +
                "toiletWasteMgmt " + this.toiletWasteMgmt + "\n" +
                "onlineServices " + this.onlineServices + "\n" +
                "primaryIncomeSource " + this.primaryIncomeSource + "\n" +
                "ownsLand " + this.ownsLand + "\n" +
                "ownsNoLand " + this.ownsNoLand + "\n" +
                "landLocation " + this.landLocation + "\n" +
                "landOwnedBy " + this.landOwnedBy + "\n" +
                "landUsedAs " + this.landUsedAs + "\n" +
                "landOnLease " + this.landOnLease + "\n" +
                "landOnNoLease " + this.landOnNoLease + "\n" +
                "landOnLeaseUsedAs " + this.landOnLeaseUsedAs + "\n" +
                "primaryAgroProductions " + this.primaryAgroProductions + "\n" +
                "lastYearAgroProductions " + this.lastYearAgroProductions + "\n" +
                "assetAnimals " + this.assetAnimals + "\n" +
                "assetBirds " + this.assetBirds + "\n" +
                "assetsFishes " + this.assetsFishes + "\n" +
                "assetsBees " + this.assetsBees + "\n" +
                "horticultureProductionCounts " + this.horticultureProductionCounts + "\n" +
                "sellsProductions " + this.sellsProductions + "\n" +
                "notSellsProductions " + this.notSellsProductions + "\n" +
                "sellProductionList " + this.sellProductionList + "\n" +
                "sellProductionTo " + this.sellProductionTo + "\n" +
                "hasFruitPlants " + this.hasFruitPlants + "\n" +
                "hasNoFruitPlants " + this.hasNoFruitPlants + "\n" +
                "hasVegetableCrops " + this.hasVegetableCrops + "\n" +
                "hasNoVegetableCrops " + this.hasNoVegetableCrops + "\n" +
                "lastYearIncome " + this.lastYearIncome + "\n" +
                "lastYearExpense " + this.lastYearExpense + "\n" +
                "worksAbroad " + this.worksAbroad + "\n" +
                "notWorkAbroad " + this.notWorkAbroad + "\n" +
                "remittanceSpentOn " + this.remittanceSpentOn + "\n" +
                "lastYearInvestments " + this.lastYearInvestments + "\n" +
                "productionsSustainableFor " + this.productionsSustainableFor + "\n" +
                "incomeIsSufficient " + this.incomeIsSufficient + "\n" +
                "incomeIsNotSufficient " + this.incomeIsNotSufficient + "\n" +
                "additionalCashSource " + this.additionalCashSource + "\n" +
                "hasTakenLoan " + this.hasTakenLoan + "\n" +
                "hasNotTakenLoan " + this.hasNotTakenLoan + "\n" +
                "loanSource " + this.loanSource + "\n" +
                "takenLoanFor " + this.takenLoanFor + "\n" +
                "durationToClearLoan " + this.durationToClearLoan + "\n" +
                "knowsSamhit " + this.knowsSamhit + "\n" +
                "notKnowSamhit " + this.notKnowSamhit + "\n" +
                "builtAsPerSamhit " + this.builtAsPerSamhit + "\n" +
                "notBuiltAsPerSamhit " + this.notBuiltAsPerSamhit + "\n" +
                "notKnowBuiltAsPerSamhit " + this.notKnowBuiltAsPerSamhit + "\n" +
                "hasPermissionBlueprint " + this.hasPermissionBlueprint + "\n" +
                "hasNoPermissionBlueprint " + this.hasNoPermissionBlueprint + "\n" +
                "hasSafeZoneNearHouse " + this.hasSafeZoneNearHouse + "\n" +
                "hasNoSafeZoneNearHouse " + this.hasNoSafeZoneNearHouse + "\n" +
                "houseSusceptToCalamity " + this.houseSusceptToCalamity + "\n" +
                "houseNoSusceptToCalamity " + this.houseNoSusceptToCalamity + "\n" +
                "infoAboutEarthquake " + this.infoAboutEarthquake + "\n" +
                "noInfoAboutEarthquake " + this.noInfoAboutEarthquake + "\n" +
                "knowSafeZoneInHome " + this.knowSafeZoneInHome + "\n" +
                "knowsNoSafeZoneInHome " + this.knowsNoSafeZoneInHome + "\n" +
                "hasSuppliesForSOE " + this.hasSuppliesForSOE + "\n" +
                "hasNoSuppliesForSOE " + this.hasNoSuppliesForSOE + "\n" +
                "affectedByCalamitiesList " + this.affectedByCalamitiesList + "\n" +
                "lastMajorDisaster " + this.lastMajorDisaster + "\n" +
                "hasAssetInsurance " + this.hasAssetInsurance + "\n" +
                "hasNoAssetInsurance " + this.hasNoAssetInsurance + "\n" +
                "assetsListWithInsurance " + this.assetsListWithInsurance + "\n" +
                "stepsTakenToMinimizeDisasterEffects " + this.stepsTakenToMinimizeDisasterEffects + "\n" +
                "isViolenceVictim " + this.isViolenceVictim + "\n" +
                "isNotViolenceVictim " + this.isNotViolenceVictim + "\n" +
                "deathCount " + this.deathCount + "\n" +
                "injuryCount " + this.injuryCount + "\n" +
                "missingCount " + this.missingCount + "\n" +
                "assetLossAmountRs " + this.assetLossAmountRs + "\n" +
                "isSued " + this.isSued + "\n" +
                "isNotSued " + this.isNotSued + "\n" +
                "isShifted " + this.isShifted + "\n" +
                "isNotShifted " + this.isNotShifted + "\n" +
                "isSexuallyAbuse " + this.isSexuallyAbuse + "\n" +
                "isNotSexuallyAbused " + this.isNotSexuallyAbused + "\n" +
                "memberMissing " + this.memberMissing + "\n" +
                "memberNotMissing " + this.memberNotMissing + "\n" +
                "feelsSafeInMunicipal " + this.feelsSafeInMunicipal + "\n" +
                "feelsNoSafeInMunicipal " + this.feelsNoSafeInMunicipal + "\n" +
                "reasonFeelsSafe " + this.reasonFeelsSafe + "\n" +
                "reasonNoFeelSafe " + this.reasonNoFeelSafe + "\n" +
                "wardNoWhereNotFeelSafe " + this.wardNoWhereNotFeelSafe + "\n" +
                "regionNameWhereNotFeelSafe " + this.regionNameWhereNotFeelSafe + "\n" +
                "homeMemberUptoAge16Works " + this.homeMemberUptoAge16Works + "\n" +
                "noHomeMemberUptoAge16Works " + this.noHomeMemberUptoAge16Works + "\n" +
                "uptoAge16WoringBoyCount " + this.uptoAge16WoringBoyCount + "\n" +
                "uptoAge16WorkingGirlCount " + this.uptoAge16WorkingGirlCount + "\n" +
                "memberUptoAge16HiredWork " + this.memberUptoAge16HiredWork + "\n" +
                "noMemberUptoAge16HiredWork " + this.noMemberUptoAge16HiredWork + "\n" +
                "uptoAge16HiredBoyCount " + this.uptoAge16HiredBoyCount + "\n" +
                "uptoAge16HiredGirlCount " + this.uptoAge16HiredGirlCount + "\n" +
                "childrenIsOnBadInfluence " + this.childrenIsOnBadInfluence + "\n" +
                "childrenNotOnBadInfluence " + this.childrenNotOnBadInfluence + "\n" +
                "childrenBadInfluenceOn " + this.childrenBadInfluenceOn + "\n" +
                "wayOfMakingFamilyDecision " + this.wayOfMakingFamilyDecision + "\n" +
                "assetsOnFemaleName " + this.assetsOnFemaleName + "\n" +
                "allowanceListTakenByFamily " + this.allowanceListTakenByFamily + "\n" +
                "memberHasInvolvedOnDevelopment " + this.memberHasInvolvedOnDevelopment + "\n" +
                "memberHasNotInvolvedOnDevelopment " + this.memberHasNotInvolvedOnDevelopment + "\n" +
                "opinionOnDevelopmentPriority " + this.opinionOnDevelopmentPriority + "\n";
    }

    public String getAll2() {
        return "this.hasToilet " + this.hasToilet + "\n" +
                "this.hasNoToilet " + this.hasNoToilet + "\n" +
                "this.toiletType " + this.toiletType + "\n" +
                "this.toiletWasteMgmt " + this.toiletWasteMgmt + "\n" +
                "this.onlineServices " + this.onlineServices + "\n" +
                "this.primaryIncomeSource " + this.primaryIncomeSource + "\n" +
                "this.ownsLand " + this.ownsLand + "\n" +
                "this.ownsNoLand " + this.ownsNoLand + "\n" +
                "this.landLocation " + this.landLocation + "\n" +
                "this.landOwnedBy " + this.landOwnedBy + "\n" +
                "this.landUsedAs " + this.landUsedAs + "\n" +
                "this.landOnLease " + this.landOnLease + "\n" +
                "this.landOnNoLease " + this.landOnNoLease + "\n" +
                "this.landOnLeaseUsedAs " + this.landOnLeaseUsedAs + "\n" +
                "this.primaryAgroProductions " + this.primaryAgroProductions + "\n" +
                "this.lastYearAgroProductions " + this.lastYearAgroProductions + "\n" +
                "this.assetAnimals " + this.assetAnimals + "\n" +
                "this.assetBirds " + this.assetBirds + "\n" +
                "this.assetsFishes " + this.assetsFishes + "\n" +
                "this.assetsBees " + this.assetsBees + "\n" +
                "this.horticultureProductionCounts " + this.horticultureProductionCounts + "\n" +
                "this.sellsProductions " + this.sellsProductions + "\n" +
                "this.notSellsProductions " + this.notSellsProductions + "\n" +
                "this.sellProductionList " + this.sellProductionList + "\n" +
                "this.sellProductionTo " + this.sellProductionTo + "\n" +
                "this.hasFruitPlants " + this.hasFruitPlants + "\n" +
                "this.hasNoFruitPlants " + this.hasNoFruitPlants + "\n" +
                "this.hasVegetableCrops " + this.hasVegetableCrops + "\n" +
                "this.hasNoVegetableCrops " + this.hasNoVegetableCrops + "\n" +
                "this.lastYearIncome " + this.lastYearIncome + "\n" +
                "this.lastYearExpense " + this.lastYearExpense + "\n" +
                "this.worksAbroad " + this.worksAbroad + "\n" +
                "this.notWorkAbroad " + this.notWorkAbroad + "\n" +
                "this.remittanceSpentOn " + this.remittanceSpentOn + "\n" +
                "this.lastYearInvestments " + this.lastYearInvestments + "\n" +
                "this.productionsSustainableFor " + this.productionsSustainableFor + "\n" +
                "this.incomeIsSufficient " + this.incomeIsSufficient + "\n" +
                "this.incomeIsNotSufficient " + this.incomeIsNotSufficient + "\n" +
                "this.additionalCashSource " + this.additionalCashSource + "\n" +
                "this.hasTakenLoan " + this.hasTakenLoan + "\n" +
                "this.hasNotTakenLoan " + this.hasNotTakenLoan + "\n" +
                "this.loanSource " + this.loanSource + "\n" +
                "this.takenLoanFor " + this.takenLoanFor + "\n" +
                "this.durationToClearLoan " + this.durationToClearLoan + "\n" +
                "this.knowsSamhit " + this.knowsSamhit + "\n" +
                "this.notKnowSamhit " + this.notKnowSamhit + "\n" +
                "this.builtAsPerSamhit " + this.builtAsPerSamhit + "\n" +
                "this.notBuiltAsPerSamhit " + this.notBuiltAsPerSamhit + "\n" +
                "this.notKnowBuiltAsPerSamhit " + this.notKnowBuiltAsPerSamhit + "\n" +
                "this.hasPermissionBlueprint " + this.hasPermissionBlueprint + "\n" +
                "this.hasNoPermissionBlueprint " + this.hasNoPermissionBlueprint + "\n" +
                "this.hasSafeZoneNearHouse " + this.hasSafeZoneNearHouse + "\n" +
                "this.hasNoSafeZoneNearHouse " + this.hasNoSafeZoneNearHouse + "\n" +
                "this.houseSusceptToCalamity " + this.houseSusceptToCalamity + "\n" +
                "this.houseNoSusceptToCalamity " + this.houseNoSusceptToCalamity + "\n" +
                "this.infoAboutEarthquake " + this.infoAboutEarthquake + "\n" +
                "this.noInfoAboutEarthquake " + this.noInfoAboutEarthquake + "\n" +
                "this.knowSafeZoneInHome " + this.knowSafeZoneInHome + "\n" +
                "this.knowsNoSafeZoneInHome " + this.knowsNoSafeZoneInHome + "\n" +
                "this.hasSuppliesForSOE " + this.hasSuppliesForSOE + "\n" +
                "this.hasNoSuppliesForSOE " + this.hasNoSuppliesForSOE + "\n" +
                "this.affectedByCalamitiesList " + this.affectedByCalamitiesList + "\n" +
                "this.lastMajorDisaster " + this.lastMajorDisaster + "\n" +
                "this.hasAssetInsurance " + this.hasAssetInsurance + "\n" +
                "this.hasNoAssetInsurance " + this.hasNoAssetInsurance + "\n" +
                "this.assetsListWithInsurance " + this.assetsListWithInsurance + "\n" +
                "this.stepsTakenToMinimizeDisasterEffects " + this.stepsTakenToMinimizeDisasterEffects + "\n" +
                "this.isViolenceVictim " + this.isViolenceVictim + "\n" +
                "this.isNotViolenceVictim " + this.isNotViolenceVictim + "\n" +
                "this.deathCount " + this.deathCount + "\n" +
                "this.injuryCount " + this.injuryCount + "\n" +
                "this.missingCount " + this.missingCount + "\n" +
                "this.assetLossAmountRs " + this.assetLossAmountRs + "\n" +
                "this.isSued " + this.isSued + "\n" +
                "this.isNotSued " + this.isNotSued + "\n" +
                "this.isShifted " + this.isShifted + "\n" +
                "this.isNotShifted " + this.isNotShifted + "\n" +
                "this.isSexuallyAbuse " + this.isSexuallyAbuse + "\n" +
                "this.isNotSexuallyAbused " + this.isNotSexuallyAbused + "\n" +
                "this.memberMissing " + this.memberMissing + "\n" +
                "this.memberNotMissing " + this.memberNotMissing + "\n" +
                "this.feelsSafeInMunicipal " + this.feelsSafeInMunicipal + "\n" +
                "this.feelsNoSafeInMunicipal " + this.feelsNoSafeInMunicipal + "\n" +
                "this.reasonFeelsSafe " + this.reasonFeelsSafe + "\n" +
                "this.reasonNoFeelSafe " + this.reasonNoFeelSafe + "\n" +
                "this.wardNoWhereNotFeelSafe " + this.wardNoWhereNotFeelSafe + "\n" +
                "this.regionNameWhereNotFeelSafe " + this.regionNameWhereNotFeelSafe + "\n" +
                "this.homeMemberUptoAge16Works " + this.homeMemberUptoAge16Works + "\n" +
                "this.noHomeMemberUptoAge16Works " + this.noHomeMemberUptoAge16Works + "\n" +
                "this.uptoAge16WoringBoyCount " + this.uptoAge16WoringBoyCount + "\n" +
                "this.uptoAge16WorkingGirlCount " + this.uptoAge16WorkingGirlCount + "\n" +
                "this.memberUptoAge16HiredWork " + this.memberUptoAge16HiredWork + "\n" +
                "this.noMemberUptoAge16HiredWork " + this.noMemberUptoAge16HiredWork + "\n" +
                "this.uptoAge16HiredBoyCount " + this.uptoAge16HiredBoyCount + "\n" +
                "this.uptoAge16HiredGirlCount " + this.uptoAge16HiredGirlCount + "\n" +
                "this.childrenIsOnBadInfluence " + this.childrenIsOnBadInfluence + "\n" +
                "this.childrenNotOnBadInfluence " + this.childrenNotOnBadInfluence + "\n" +
                "this.childrenBadInfluenceOn " + this.childrenBadInfluenceOn + "\n" +
                "this.wayOfMakingFamilyDecision " + this.wayOfMakingFamilyDecision + "\n" +
                "this.assetsOnFemaleName " + this.assetsOnFemaleName + "\n" +
                "this.allowanceListTakenByFamily " + this.allowanceListTakenByFamily + "\n" +
                "this.memberHasInvolvedOnDevelopment " + this.memberHasInvolvedOnDevelopment + "\n" +
                "this.memberHasNotInvolvedOnDevelopment " + this.memberHasNotInvolvedOnDevelopment + "\n" +
                "this.opinionOnDevelopmentPriority " + this.opinionOnDevelopmentPriority + "\n" +
                "this.annabali_muri " + this.annabali_muri + "\n" +
                "this.dhan_muri " + this.dhan_muri + "\n" +
                "this.makai_muri " + this.makai_muri + "\n" +
                "this.kodo_muri " + this.kodo_muri + "\n" +
                "this.ghau_muri " + this.ghau_muri + "\n" +
                "this.fapar_muri " + this.fapar_muri + "\n" +
                "this.others_muri " + this.others_muri + "\n" +
                "this.oil_kg " + this.oil_kg + "\n" +
                "this.daal_kg " + this.daal_kg + "\n" +
                "this.tarkari_kg " + this.tarkari_kg + "\n" +
                "this.freshTarkari_kg " + this.freshTarkari_kg + "\n" +
                "this.aalu_kg " + this.aalu_kg + "\n" +
                "this.masala_kg " + this.masala_kg + "\n" +
                "this.falful_kg " + this.falful_kg + "\n" +
                "this.kandamul_kg " + this.kandamul_kg + "\n" +
                "this.others_kg " + this.others_kg + "\n" +
                "this.cow_sthaniya " + this.cow_sthaniya + "\n" +
                "this.cow_unnat " + this.cow_unnat + "\n" +
                "this.buffalo_sthaniya " + this.buffalo_sthaniya + "\n" +
                "this.buffalo_unnat " + this.buffalo_unnat + "\n" +
                "this.goat_sthaniya " + this.goat_sthaniya + "\n" +
                "this.goat_unnat " + this.goat_unnat + "\n" +
                "this.sheep_sthaniya " + this.sheep_sthaniya + "\n" +
                "this.sheep_unnat " + this.sheep_unnat + "\n" +
                "this.pig_sthaniya " + this.pig_sthaniya + "\n" +
                "this.pig_unnat " + this.pig_unnat + "\n" +
                "this.other_animal " + this.other_animal + "\n" +
                "this.hen_sthaniya " + this.hen_sthaniya + "\n" +
                "this.hen_unnat " + this.hen_unnat + "\n" +
                "this.pigeon_count " + this.pigeon_count + "\n" +
                "this.other_birds " + this.other_birds + "\n" +
                "this.fish_count " + this.fish_count + "\n" +
                "this.beehive_count " + this.beehive_count + "\n" +
                "this.other_pasupanchi " + this.other_pasupanchi + "\n" +
                "this.milkCurd_litre " + this.milkCurd_litre + "\n" +
                "this.ghee_kg " + this.ghee_kg + "\n" +
                "this.otherDairy_kg " + this.otherDairy_kg + "\n" +
                "this.meat_kg " + this.meat_kg + "\n" +
                "this.compost_quintal " + this.compost_quintal + "\n" +
                "this.urine_litre " + this.urine_litre + "\n" +
                "this.wool_kg " + this.wool_kg + "\n" +
                "this.egg_crate " + this.egg_crate + "\n" +
                "this.fish_kg " + this.fish_kg + "\n" +
                "this.honey_kg " + this.honey_kg + "\n" +
                "this.other_production_kg " + this.other_production_kg + "\n" +
                "this.incomeAgriculture " + this.incomeAgriculture + "\n" +
                "this.incomeBusiness " + this.incomeBusiness + "\n" +
                "this.incomeSalaryPension " + this.incomeSalaryPension + "\n" +
                "this.incomeSocialAllowance " + this.incomeSocialAllowance + "\n" +
                "this.incomeForeignEmp " + this.incomeForeignEmp + "\n" +
                "this.incomeWages " + this.incomeWages + "\n" +
                "this.incomeRent " + this.incomeRent + "\n" +
                "this.incomeInterestInvest " + this.incomeInterestInvest + "\n" +
                "this.incomeOthers " + this.incomeOthers + "\n" +
                "this.expenseFood " + this.expenseFood + "\n" +
                "this.expenseCloth " + this.expenseCloth + "\n" +
                "this.expenseEducation " + this.expenseEducation + "\n" +
                "this.expenseHealth " + this.expenseHealth + "\n" +
                "this.expenseEntertain " + this.expenseEntertain + "\n" +
                "this.expenseRent " + this.expenseRent + "\n" +
                "this.expenseAgriculture " + this.expenseAgriculture + "\n" +
                "this.expenseInstallment " + this.expenseInstallment + "\n" +
                "this.expenseFuel " + this.expenseFuel + "\n" +
                "this.expenseTranport " + this.expenseTranport + "\n" +
                "this.expenseOthers " + this.expenseOthers + "\n";
    }

    public String getHasToilet() {
        return hasToilet;
    }

    public void setHasToilet(String hasToilet) {
        this.hasToilet = hasToilet;
    }

    public String getHasNoToilet() {
        return hasNoToilet;
    }

    public void setHasNoToilet(String hasNoToilet) {
        this.hasNoToilet = hasNoToilet;
    }

    public String getToiletType() {
        return toiletType;
    }

    public void setToiletType(String toiletType) {
        this.toiletType = toiletType;
    }

    public String getToiletWasteMgmt() {
        return toiletWasteMgmt;
    }

    public void setToiletWasteMgmt(String toiletWasteMgmt) {
        this.toiletWasteMgmt = toiletWasteMgmt;
    }

    public String getOnlineServices() {
        return onlineServices;
    }

    public void setOnlineServices(String onlineServices) {
        this.onlineServices = onlineServices;
    }

    public String getPrimaryIncomeSource() {
        return primaryIncomeSource;
    }

    public void setPrimaryIncomeSource(String primaryIncomeSource) {
        this.primaryIncomeSource = primaryIncomeSource;
    }

    public String getOwnsLand() {
        return ownsLand;
    }

    public void setOwnsLand(String ownsLand) {
        this.ownsLand = ownsLand;
    }

    public String getOwnsNoLand() {
        return ownsNoLand;
    }

    public void setOwnsNoLand(String ownsNoLand) {
        this.ownsNoLand = ownsNoLand;
    }

    public String getLandLocation() {
        return landLocation;
    }

    public void setLandLocation(String landLocation) {
        this.landLocation = landLocation;
    }

    public String getLandOwnedBy() {
        return landOwnedBy;
    }

    public void setLandOwnedBy(String landOwnedBy) {
        this.landOwnedBy = landOwnedBy;
    }

    public String getLandUsedAs() {
        return landUsedAs;
    }

    public void setLandUsedAs(String landUsedAs) {
        this.landUsedAs = landUsedAs;
    }

    public String getLandOnLease() {
        return landOnLease;
    }

    public void setLandOnLease(String landOnLease) {
        this.landOnLease = landOnLease;
    }

    public String getLandOnNoLease() {
        return landOnNoLease;
    }

    public void setLandOnNoLease(String landOnNoLease) {
        this.landOnNoLease = landOnNoLease;
    }

    public String getLandOnLeaseUsedAs() {
        return landOnLeaseUsedAs;
    }

    public void setLandOnLeaseUsedAs(String landOnLeaseUsedAs) {
        this.landOnLeaseUsedAs = landOnLeaseUsedAs;
    }

    public String getPrimaryAgroProductions() {
        return primaryAgroProductions;
    }

    public void setPrimaryAgroProductions(String primaryAgroProductions) {
        this.primaryAgroProductions = primaryAgroProductions;
    }

    public String getLastYearAgroProductions() {
        return lastYearAgroProductions;
    }

    public void setLastYearAgroProductions(String lastYearAgroProductions) {
        this.lastYearAgroProductions = lastYearAgroProductions;
    }

    public String getAssetAnimals() {
        return assetAnimals;
    }

    public void setAssetAnimals(String assetAnimals) {
        this.assetAnimals = assetAnimals;
    }

    public String getAssetBirds() {
        return assetBirds;
    }

    public void setAssetBirds(String assetBirds) {
        this.assetBirds = assetBirds;
    }

    public String getAssetsFishes() {
        return assetsFishes;
    }

    public void setAssetsFishes(String assetsFishes) {
        this.assetsFishes = assetsFishes;
    }

    public String getAssetsBees() {
        return assetsBees;
    }

    public void setAssetsBees(String assetsBees) {
        this.assetsBees = assetsBees;
    }

    public String getHorticultureProductionCounts() {
        return horticultureProductionCounts;
    }

    public void setHorticultureProductionCounts(String horticultureProductionCounts) {
        this.horticultureProductionCounts = horticultureProductionCounts;
    }

    public String getSellsProductions() {
        return sellsProductions;
    }

    public void setSellsProductions(String sellsProductions) {
        this.sellsProductions = sellsProductions;
    }

    public String getNotSellsProductions() {
        return notSellsProductions;
    }

    public void setNotSellsProductions(String notSellsProductions) {
        this.notSellsProductions = notSellsProductions;
    }

    public String getSellProductionList() {
        return sellProductionList;
    }

    public void setSellProductionList(String sellProductionList) {
        this.sellProductionList = sellProductionList;
    }

    public String getSellProductionTo() {
        return sellProductionTo;
    }

    public void setSellProductionTo(String sellProductionTo) {
        this.sellProductionTo = sellProductionTo;
    }

    public String getHasFruitPlants() {
        return hasFruitPlants;
    }

    public void setHasFruitPlants(String hasFruitPlants) {
        this.hasFruitPlants = hasFruitPlants;
    }

    public String getHasNoFruitPlants() {
        return hasNoFruitPlants;
    }

    public void setHasNoFruitPlants(String hasNoFruitPlants) {
        this.hasNoFruitPlants = hasNoFruitPlants;
    }

    public String getHasVegetableCrops() {
        return hasVegetableCrops;
    }

    public void setHasVegetableCrops(String hasVegetableCrops) {
        this.hasVegetableCrops = hasVegetableCrops;
    }

    public String getHasNoVegetableCrops() {
        return hasNoVegetableCrops;
    }

    public void setHasNoVegetableCrops(String hasNoVegetableCrops) {
        this.hasNoVegetableCrops = hasNoVegetableCrops;
    }

    public String getLastYearIncome() {
        return lastYearIncome;
    }

    public void setLastYearIncome(String lastYearIncome) {
        this.lastYearIncome = lastYearIncome;
    }

    public String getLastYearExpense() {
        return lastYearExpense;
    }

    public void setLastYearExpense(String lastYearExpense) {
        this.lastYearExpense = lastYearExpense;
    }

    public String getWorksAbroad() {
        return worksAbroad;
    }

    public void setWorksAbroad(String worksAbroad) {
        this.worksAbroad = worksAbroad;
    }

    public String getNotWorkAbroad() {
        return notWorkAbroad;
    }

    public void setNotWorkAbroad(String notWorkAbroad) {
        this.notWorkAbroad = notWorkAbroad;
    }

    public String getRemittanceSpentOn() {
        return remittanceSpentOn;
    }

    public void setRemittanceSpentOn(String remittanceSpentOn) {
        this.remittanceSpentOn = remittanceSpentOn;
    }

    public String getLastYearInvestments() {
        return lastYearInvestments;
    }

    public void setLastYearInvestments(String lastYearInvestments) {
        this.lastYearInvestments = lastYearInvestments;
    }

    public String getProductionsSustainableFor() {
        return productionsSustainableFor;
    }

    public void setProductionsSustainableFor(String productionsSustainableFor) {
        this.productionsSustainableFor = productionsSustainableFor;
    }

    public String getIncomeIsSufficient() {
        return incomeIsSufficient;
    }

    public void setIncomeIsSufficient(String incomeIsSufficient) {
        this.incomeIsSufficient = incomeIsSufficient;
    }

    public String getIncomeIsNotSufficient() {
        return incomeIsNotSufficient;
    }

    public void setIncomeIsNotSufficient(String incomeIsNotSufficient) {
        this.incomeIsNotSufficient = incomeIsNotSufficient;
    }

    public String getAdditionalCashSource() {
        return additionalCashSource;
    }

    public void setAdditionalCashSource(String additionalCashSource) {
        this.additionalCashSource = additionalCashSource;
    }

    public String getHasTakenLoan() {
        return hasTakenLoan;
    }

    public void setHasTakenLoan(String hasTakenLoan) {
        this.hasTakenLoan = hasTakenLoan;
    }

    public String getHasNotTakenLoan() {
        return hasNotTakenLoan;
    }

    public void setHasNotTakenLoan(String hasNotTakenLoan) {
        this.hasNotTakenLoan = hasNotTakenLoan;
    }

    public String getLoanSource() {
        return loanSource;
    }

    public void setLoanSource(String loanSource) {
        this.loanSource = loanSource;
    }

    public String getTakenLoanFor() {
        return takenLoanFor;
    }

    public void setTakenLoanFor(String takenLoanFor) {
        this.takenLoanFor = takenLoanFor;
    }

    public String getDurationToClearLoan() {
        return durationToClearLoan;
    }

    public void setDurationToClearLoan(String durationToClearLoan) {
        this.durationToClearLoan = durationToClearLoan;
    }

    public String getKnowsSamhit() {
        return knowsSamhit;
    }

    public void setKnowsSamhit(String knowsSamhit) {
        this.knowsSamhit = knowsSamhit;
    }

    public String getNotKnowSamhit() {
        return notKnowSamhit;
    }

    public void setNotKnowSamhit(String notKnowSamhit) {
        this.notKnowSamhit = notKnowSamhit;
    }

    public String getBuiltAsPerSamhit() {
        return builtAsPerSamhit;
    }

    public void setBuiltAsPerSamhit(String builtAsPerSamhit) {
        this.builtAsPerSamhit = builtAsPerSamhit;
    }

    public String getNotBuiltAsPerSamhit() {
        return notBuiltAsPerSamhit;
    }

    public void setNotBuiltAsPerSamhit(String notBuiltAsPerSamhit) {
        this.notBuiltAsPerSamhit = notBuiltAsPerSamhit;
    }

    public String getNotKnowBuiltAsPerSamhit() {
        return notKnowBuiltAsPerSamhit;
    }

    public void setNotKnowBuiltAsPerSamhit(String notKnowBuiltAsPerSamhit) {
        this.notKnowBuiltAsPerSamhit = notKnowBuiltAsPerSamhit;
    }

    public String getHasPermissionBlueprint() {
        return hasPermissionBlueprint;
    }

    public void setHasPermissionBlueprint(String hasPermissionBlueprint) {
        this.hasPermissionBlueprint = hasPermissionBlueprint;
    }

    public String getHasNoPermissionBlueprint() {
        return hasNoPermissionBlueprint;
    }

    public void setHasNoPermissionBlueprint(String hasNoPermissionBlueprint) {
        this.hasNoPermissionBlueprint = hasNoPermissionBlueprint;
    }

    public String getHasSafeZoneNearHouse() {
        return hasSafeZoneNearHouse;
    }

    public void setHasSafeZoneNearHouse(String hasSafeZoneNearHouse) {
        this.hasSafeZoneNearHouse = hasSafeZoneNearHouse;
    }

    public String getHasNoSafeZoneNearHouse() {
        return hasNoSafeZoneNearHouse;
    }

    public void setHasNoSafeZoneNearHouse(String hasNoSafeZoneNearHouse) {
        this.hasNoSafeZoneNearHouse = hasNoSafeZoneNearHouse;
    }

    public String getHouseSusceptToCalamity() {
        return houseSusceptToCalamity;
    }

    public void setHouseSusceptToCalamity(String houseSusceptToCalamity) {
        this.houseSusceptToCalamity = houseSusceptToCalamity;
    }

    public String getHouseNoSusceptToCalamity() {
        return houseNoSusceptToCalamity;
    }

    public void setHouseNoSusceptToCalamity(String houseNoSusceptToCalamity) {
        this.houseNoSusceptToCalamity = houseNoSusceptToCalamity;
    }

    public String getInfoAboutEarthquake() {
        return infoAboutEarthquake;
    }

    public void setInfoAboutEarthquake(String infoAboutEarthquake) {
        this.infoAboutEarthquake = infoAboutEarthquake;
    }

    public String getNoInfoAboutEarthquake() {
        return noInfoAboutEarthquake;
    }

    public void setNoInfoAboutEarthquake(String noInfoAboutEarthquake) {
        this.noInfoAboutEarthquake = noInfoAboutEarthquake;
    }

    public String getKnowSafeZoneInHome() {
        return knowSafeZoneInHome;
    }

    public void setKnowSafeZoneInHome(String knowSafeZoneInHome) {
        this.knowSafeZoneInHome = knowSafeZoneInHome;
    }

    public String getKnowsNoSafeZoneInHome() {
        return knowsNoSafeZoneInHome;
    }

    public void setKnowsNoSafeZoneInHome(String knowsNoSafeZoneInHome) {
        this.knowsNoSafeZoneInHome = knowsNoSafeZoneInHome;
    }

    public String getHasSuppliesForSOE() {
        return hasSuppliesForSOE;
    }

    public void setHasSuppliesForSOE(String hasSuppliesForSOE) {
        this.hasSuppliesForSOE = hasSuppliesForSOE;
    }

    public String getHasNoSuppliesForSOE() {
        return hasNoSuppliesForSOE;
    }

    public void setHasNoSuppliesForSOE(String hasNoSuppliesForSOE) {
        this.hasNoSuppliesForSOE = hasNoSuppliesForSOE;
    }

    public String getAffectedByCalamitiesList() {
        return affectedByCalamitiesList;
    }

    public void setAffectedByCalamitiesList(String affectedByCalamitiesList) {
        this.affectedByCalamitiesList = affectedByCalamitiesList;
    }

    public String getLastMajorDisaster() {
        return lastMajorDisaster;
    }

    public void setLastMajorDisaster(String lastMajorDisaster) {
        this.lastMajorDisaster = lastMajorDisaster;
    }

    public String getHasAssetInsurance() {
        return hasAssetInsurance;
    }

    public void setHasAssetInsurance(String hasAssetInsurance) {
        this.hasAssetInsurance = hasAssetInsurance;
    }

    public String getHasNoAssetInsurance() {
        return hasNoAssetInsurance;
    }

    public void setHasNoAssetInsurance(String hasNoAssetInsurance) {
        this.hasNoAssetInsurance = hasNoAssetInsurance;
    }

    public String getAssetsListWithInsurance() {
        return assetsListWithInsurance;
    }

    public void setAssetsListWithInsurance(String assetsListWithInsurance) {
        this.assetsListWithInsurance = assetsListWithInsurance;
    }

    public String getStepsTakenToMinimizeDisasterEffects() {
        return stepsTakenToMinimizeDisasterEffects;
    }

    public void setStepsTakenToMinimizeDisasterEffects(String stepsTakenToMinimizeDisasterEffects) {
        this.stepsTakenToMinimizeDisasterEffects = stepsTakenToMinimizeDisasterEffects;
    }

    public String getIsViolenceVictim() {
        return isViolenceVictim;
    }

    public void setIsViolenceVictim(String isViolenceVictim) {
        this.isViolenceVictim = isViolenceVictim;
    }

    public String getIsNotViolenceVictim() {
        return isNotViolenceVictim;
    }

    public void setIsNotViolenceVictim(String isNotViolenceVictim) {
        this.isNotViolenceVictim = isNotViolenceVictim;
    }

    public String getDeathCount() {
        return deathCount;
    }

    public void setDeathCount(String deathCount) {
        this.deathCount = deathCount;
    }

    public String getInjuryCount() {
        return injuryCount;
    }

    public void setInjuryCount(String injuryCount) {
        this.injuryCount = injuryCount;
    }

    public String getMissingCount() {
        return missingCount;
    }

    public void setMissingCount(String missingCount) {
        this.missingCount = missingCount;
    }

    public String getAssetLossAmountRs() {
        return assetLossAmountRs;
    }

    public void setAssetLossAmountRs(String assetLossAmountRs) {
        this.assetLossAmountRs = assetLossAmountRs;
    }

    public String getIsSued() {
        return isSued;
    }

    public void setIsSued(String isSued) {
        this.isSued = isSued;
    }

    public String getIsNotSued() {
        return isNotSued;
    }

    public void setIsNotSued(String isNotSued) {
        this.isNotSued = isNotSued;
    }

    public String getIsShifted() {
        return isShifted;
    }

    public void setIsShifted(String isShifted) {
        this.isShifted = isShifted;
    }

    public String getIsNotShifted() {
        return isNotShifted;
    }

    public void setIsNotShifted(String isNotShifted) {
        this.isNotShifted = isNotShifted;
    }

    public String getIsSexuallyAbuse() {
        return isSexuallyAbuse;
    }

    public void setIsSexuallyAbuse(String isSexuallyAbuse) {
        this.isSexuallyAbuse = isSexuallyAbuse;
    }

    public String getIsNotSexuallyAbused() {
        return isNotSexuallyAbused;
    }

    public void setIsNotSexuallyAbused(String isNotSexuallyAbused) {
        this.isNotSexuallyAbused = isNotSexuallyAbused;
    }

    public String getMemberMissing() {
        return memberMissing;
    }

    public void setMemberMissing(String memberMissing) {
        this.memberMissing = memberMissing;
    }

    public String getMemberNotMissing() {
        return memberNotMissing;
    }

    public void setMemberNotMissing(String memberNotMissing) {
        this.memberNotMissing = memberNotMissing;
    }

    public String getFeelsSafeInMunicipal() {
        return feelsSafeInMunicipal;
    }

    public void setFeelsSafeInMunicipal(String feelsSafeInMunicipal) {
        this.feelsSafeInMunicipal = feelsSafeInMunicipal;
    }

    public String getFeelsNoSafeInMunicipal() {
        return feelsNoSafeInMunicipal;
    }

    public void setFeelsNoSafeInMunicipal(String feelsNoSafeInMunicipal) {
        this.feelsNoSafeInMunicipal = feelsNoSafeInMunicipal;
    }

    public String getReasonFeelsSafe() {
        return reasonFeelsSafe;
    }

    public void setReasonFeelsSafe(String reasonFeelsSafe) {
        this.reasonFeelsSafe = reasonFeelsSafe;
    }

    public String getReasonNoFeelSafe() {
        return reasonNoFeelSafe;
    }

    public void setReasonNoFeelSafe(String reasonNoFeelSafe) {
        this.reasonNoFeelSafe = reasonNoFeelSafe;
    }

    public String getWardNoWhereNotFeelSafe() {
        return wardNoWhereNotFeelSafe;
    }

    public void setWardNoWhereNotFeelSafe(String wardNoWhereNotFeelSafe) {
        this.wardNoWhereNotFeelSafe = wardNoWhereNotFeelSafe;
    }

    public String getRegionNameWhereNotFeelSafe() {
        return regionNameWhereNotFeelSafe;
    }

    public void setRegionNameWhereNotFeelSafe(String regionNameWhereNotFeelSafe) {
        this.regionNameWhereNotFeelSafe = regionNameWhereNotFeelSafe;
    }

    public String getHomeMemberUptoAge16Works() {
        return homeMemberUptoAge16Works;
    }

    public void setHomeMemberUptoAge16Works(String homeMemberUptoAge16Works) {
        this.homeMemberUptoAge16Works = homeMemberUptoAge16Works;
    }

    public String getUptoAge16WoringBoyCount() {
        return uptoAge16WoringBoyCount;
    }

    public void setUptoAge16WoringBoyCount(String uptoAge16WoringBoyCount) {
        this.uptoAge16WoringBoyCount = uptoAge16WoringBoyCount;
    }

    public String getUptoAge16WorkingGirlCount() {
        return uptoAge16WorkingGirlCount;
    }

    public void setUptoAge16WorkingGirlCount(String uptoAge16WorkingGirlCount) {
        this.uptoAge16WorkingGirlCount = uptoAge16WorkingGirlCount;
    }

    public String getMemberUptoAge16HiredWork() {
        return memberUptoAge16HiredWork;
    }

    public void setMemberUptoAge16HiredWork(String memberUptoAge16HiredWork) {
        this.memberUptoAge16HiredWork = memberUptoAge16HiredWork;
    }

    public String getUptoAge16HiredBoyCount() {
        return uptoAge16HiredBoyCount;
    }

    public void setUptoAge16HiredBoyCount(String uptoAge16HiredBoyCount) {
        this.uptoAge16HiredBoyCount = uptoAge16HiredBoyCount;
    }

    public String getUptoAge16HiredGirlCount() {
        return uptoAge16HiredGirlCount;
    }

    public void setUptoAge16HiredGirlCount(String uptoAge16HiredGirlCount) {
        this.uptoAge16HiredGirlCount = uptoAge16HiredGirlCount;
    }

    public String getChildrenIsOnBadInfluence() {
        return childrenIsOnBadInfluence;
    }

    public void setChildrenIsOnBadInfluence(String childrenIsOnBadInfluence) {
        this.childrenIsOnBadInfluence = childrenIsOnBadInfluence;
    }

    public String getChildrenNotOnBadInfluence() {
        return childrenNotOnBadInfluence;
    }

    public void setChildrenNotOnBadInfluence(String childrenNotOnBadInfluence) {
        this.childrenNotOnBadInfluence = childrenNotOnBadInfluence;
    }

    public String getChildrenBadInfluenceOn() {
        return childrenBadInfluenceOn;
    }

    public void setChildrenBadInfluenceOn(String childrenBadInfluenceOn) {
        this.childrenBadInfluenceOn = childrenBadInfluenceOn;
    }

    public String getWayOfMakingFamilyDecision() {
        return wayOfMakingFamilyDecision;
    }

    public void setWayOfMakingFamilyDecision(String wayOfMakingFamilyDecision) {
        this.wayOfMakingFamilyDecision = wayOfMakingFamilyDecision;
    }

    public String getAssetsOnFemaleName() {
        return assetsOnFemaleName;
    }

    public void setAssetsOnFemaleName(String assetsOnFemaleName) {
        this.assetsOnFemaleName = assetsOnFemaleName;
    }

    public String getAllowanceListTakenByFamily() {
        return allowanceListTakenByFamily;
    }

    public void setAllowanceListTakenByFamily(String allowanceListTakenByFamily) {
        this.allowanceListTakenByFamily = allowanceListTakenByFamily;
    }

    public String getMemberHasInvolvedOnDevelopment() {
        return memberHasInvolvedOnDevelopment;
    }

    public void setMemberHasInvolvedOnDevelopment(String memberHasInvolvedOnDevelopment) {
        this.memberHasInvolvedOnDevelopment = memberHasInvolvedOnDevelopment;
    }

    public String getMemberHasNotInvolvedOnDevelopment() {
        return memberHasNotInvolvedOnDevelopment;
    }

    public void setMemberHasNotInvolvedOnDevelopment(String memberHasNotInvolvedOnDevelopment) {
        this.memberHasNotInvolvedOnDevelopment = memberHasNotInvolvedOnDevelopment;
    }

    public String getOpinionOnDevelopmentPriority() {
        return opinionOnDevelopmentPriority;
    }

    public void setOpinionOnDevelopmentPriority(String opinionOnDevelopmentPriority) {
        this.opinionOnDevelopmentPriority = opinionOnDevelopmentPriority;
    }

    public String getNoHomeMemberUptoAge16Works() {
        return noHomeMemberUptoAge16Works;
    }

    public void setNoHomeMemberUptoAge16Works(String noHomeMemberUptoAge16Works) {
        this.noHomeMemberUptoAge16Works = noHomeMemberUptoAge16Works;
    }

    public String getNoMemberUptoAge16HiredWork() {
        return noMemberUptoAge16HiredWork;
    }

    public void setNoMemberUptoAge16HiredWork(String noMemberUptoAge16HiredWork) {
        this.noMemberUptoAge16HiredWork = noMemberUptoAge16HiredWork;
    }

    public String getAnnabali_muri() {
        return annabali_muri;
    }

    public void setAnnabali_muri(String annabali_muri) {
        this.annabali_muri = annabali_muri;
    }

    public String getDhan_muri() {
        return dhan_muri;
    }

    public void setDhan_muri(String dhan_muri) {
        this.dhan_muri = dhan_muri;
    }

    public String getMakai_muri() {
        return makai_muri;
    }

    public void setMakai_muri(String makai_muri) {
        this.makai_muri = makai_muri;
    }

    public String getKodo_muri() {
        return kodo_muri;
    }

    public void setKodo_muri(String kodo_muri) {
        this.kodo_muri = kodo_muri;
    }

    public String getGhau_muri() {
        return ghau_muri;
    }

    public void setGhau_muri(String ghau_muri) {
        this.ghau_muri = ghau_muri;
    }

    public String getFapar_muri() {
        return fapar_muri;
    }

    public void setFapar_muri(String fapar_muri) {
        this.fapar_muri = fapar_muri;
    }

    public String getOthers_muri() {
        return others_muri;
    }

    public void setOthers_muri(String others_muri) {
        this.others_muri = others_muri;
    }

    public String getOil_kg() {
        return oil_kg;
    }

    public void setOil_kg(String oil_kg) {
        this.oil_kg = oil_kg;
    }

    public String getDaal_kg() {
        return daal_kg;
    }

    public void setDaal_kg(String daal_kg) {
        this.daal_kg = daal_kg;
    }

    public String getTarkari_kg() {
        return tarkari_kg;
    }

    public void setTarkari_kg(String tarkari_kg) {
        this.tarkari_kg = tarkari_kg;
    }

    public String getFreshTarkari_kg() {
        return freshTarkari_kg;
    }

    public void setFreshTarkari_kg(String freshTarkari_kg) {
        this.freshTarkari_kg = freshTarkari_kg;
    }

    public String getAalu_kg() {
        return aalu_kg;
    }

    public void setAalu_kg(String aalu_kg) {
        this.aalu_kg = aalu_kg;
    }

    public String getMasala_kg() {
        return masala_kg;
    }

    public void setMasala_kg(String masala_kg) {
        this.masala_kg = masala_kg;
    }

    public String getFalful_kg() {
        return falful_kg;
    }

    public void setFalful_kg(String falful_kg) {
        this.falful_kg = falful_kg;
    }

    public String getKandamul_kg() {
        return kandamul_kg;
    }

    public void setKandamul_kg(String kandamul_kg) {
        this.kandamul_kg = kandamul_kg;
    }

    public String getOthers_kg() {
        return others_kg;
    }

    public void setOthers_kg(String others_kg) {
        this.others_kg = others_kg;
    }

    public String getCow_sthaniya() {
        return cow_sthaniya;
    }

    public void setCow_sthaniya(String cow_sthaniya) {
        this.cow_sthaniya = cow_sthaniya;
    }

    public String getCow_unnat() {
        return cow_unnat;
    }

    public void setCow_unnat(String cow_unnat) {
        this.cow_unnat = cow_unnat;
    }

    public String getBuffalo_sthaniya() {
        return buffalo_sthaniya;
    }

    public void setBuffalo_sthaniya(String buffalo_sthaniya) {
        this.buffalo_sthaniya = buffalo_sthaniya;
    }

    public String getBuffalo_unnat() {
        return buffalo_unnat;
    }

    public void setBuffalo_unnat(String buffalo_unnat) {
        this.buffalo_unnat = buffalo_unnat;
    }

    public String getGoat_sthaniya() {
        return goat_sthaniya;
    }

    public void setGoat_sthaniya(String goat_sthaniya) {
        this.goat_sthaniya = goat_sthaniya;
    }

    public String getGoat_unnat() {
        return goat_unnat;
    }

    public void setGoat_unnat(String goat_unnat) {
        this.goat_unnat = goat_unnat;
    }

    public String getSheep_sthaniya() {
        return sheep_sthaniya;
    }

    public void setSheep_sthaniya(String sheep_sthaniya) {
        this.sheep_sthaniya = sheep_sthaniya;
    }

    public String getSheep_unnat() {
        return sheep_unnat;
    }

    public void setSheep_unnat(String sheep_unnat) {
        this.sheep_unnat = sheep_unnat;
    }

    public String getPig_sthaniya() {
        return pig_sthaniya;
    }

    public void setPig_sthaniya(String pig_sthaniya) {
        this.pig_sthaniya = pig_sthaniya;
    }

    public String getPig_unnat() {
        return pig_unnat;
    }

    public void setPig_unnat(String pig_unnat) {
        this.pig_unnat = pig_unnat;
    }

    public String getOther_animal() {
        return other_animal;
    }

    public void setOther_animal(String other_animal) {
        this.other_animal = other_animal;
    }

    public String getHen_sthaniya() {
        return hen_sthaniya;
    }

    public void setHen_sthaniya(String hen_sthaniya) {
        this.hen_sthaniya = hen_sthaniya;
    }

    public String getHen_unnat() {
        return hen_unnat;
    }

    public void setHen_unnat(String hen_unnat) {
        this.hen_unnat = hen_unnat;
    }

    public String getPigeon_count() {
        return pigeon_count;
    }

    public void setPigeon_count(String pigeon_count) {
        this.pigeon_count = pigeon_count;
    }

    public String getOther_birds() {
        return other_birds;
    }

    public void setOther_birds(String other_birds) {
        this.other_birds = other_birds;
    }

    public String getFish_count() {
        return fish_count;
    }

    public void setFish_count(String fish_count) {
        this.fish_count = fish_count;
    }

    public String getBeehive_count() {
        return beehive_count;
    }

    public void setBeehive_count(String beehive_count) {
        this.beehive_count = beehive_count;
    }

    public String getOther_pasupanchi() {
        return other_pasupanchi;
    }

    public void setOther_pasupanchi(String other_pasupanchi) {
        this.other_pasupanchi = other_pasupanchi;
    }

    public String getMilkCurd_litre() {
        return milkCurd_litre;
    }

    public void setMilkCurd_litre(String milkCurd_litre) {
        this.milkCurd_litre = milkCurd_litre;
    }

    public String getGhee_kg() {
        return ghee_kg;
    }

    public void setGhee_kg(String ghee_kg) {
        this.ghee_kg = ghee_kg;
    }

    public String getOtherDairy_kg() {
        return otherDairy_kg;
    }

    public void setOtherDairy_kg(String otherDairy_kg) {
        this.otherDairy_kg = otherDairy_kg;
    }

    public String getMeat_kg() {
        return meat_kg;
    }

    public void setMeat_kg(String meat_kg) {
        this.meat_kg = meat_kg;
    }

    public String getCompost_quintal() {
        return compost_quintal;
    }

    public void setCompost_quintal(String compost_quintal) {
        this.compost_quintal = compost_quintal;
    }

    public String getUrine_litre() {
        return urine_litre;
    }

    public void setUrine_litre(String urine_litre) {
        this.urine_litre = urine_litre;
    }

    public String getWool_kg() {
        return wool_kg;
    }

    public void setWool_kg(String wool_kg) {
        this.wool_kg = wool_kg;
    }

    public String getEgg_crate() {
        return egg_crate;
    }

    public void setEgg_crate(String egg_crate) {
        this.egg_crate = egg_crate;
    }

    public String getFish_kg() {
        return fish_kg;
    }

    public void setFish_kg(String fish_kg) {
        this.fish_kg = fish_kg;
    }

    public String getHoney_kg() {
        return honey_kg;
    }

    public void setHoney_kg(String honey_kg) {
        this.honey_kg = honey_kg;
    }

    public String getOther_production_kg() {
        return other_production_kg;
    }

    public void setOther_production_kg(String other_production_kg) {
        this.other_production_kg = other_production_kg;
    }

    public String getIncomeAgriculture() {
        return incomeAgriculture;
    }

    public void setIncomeAgriculture(String incomeAgriculture) {
        this.incomeAgriculture = incomeAgriculture;
    }

    public String getIncomeBusiness() {
        return incomeBusiness;
    }

    public void setIncomeBusiness(String incomeBusiness) {
        this.incomeBusiness = incomeBusiness;
    }

    public String getIncomeSalaryPension() {
        return incomeSalaryPension;
    }

    public void setIncomeSalaryPension(String incomeSalaryPension) {
        this.incomeSalaryPension = incomeSalaryPension;
    }

    public String getIncomeSocialAllowance() {
        return incomeSocialAllowance;
    }

    public void setIncomeSocialAllowance(String incomeSocialAllowance) {
        this.incomeSocialAllowance = incomeSocialAllowance;
    }

    public String getIncomeForeignEmp() {
        return incomeForeignEmp;
    }

    public void setIncomeForeignEmp(String incomeForeignEmp) {
        this.incomeForeignEmp = incomeForeignEmp;
    }

    public String getIncomeWages() {
        return incomeWages;
    }

    public void setIncomeWages(String incomeWages) {
        this.incomeWages = incomeWages;
    }

    public String getIncomeRent() {
        return incomeRent;
    }

    public void setIncomeRent(String incomeRent) {
        this.incomeRent = incomeRent;
    }

    public String getIncomeInterestInvest() {
        return incomeInterestInvest;
    }

    public void setIncomeInterestInvest(String incomeInterestInvest) {
        this.incomeInterestInvest = incomeInterestInvest;
    }

    public String getIncomeOthers() {
        return incomeOthers;
    }

    public void setIncomeOthers(String incomeOthers) {
        this.incomeOthers = incomeOthers;
    }

    public String getExpenseFood() {
        return expenseFood;
    }

    public void setExpenseFood(String expenseFood) {
        this.expenseFood = expenseFood;
    }

    public String getExpenseCloth() {
        return expenseCloth;
    }

    public void setExpenseCloth(String expenseCloth) {
        this.expenseCloth = expenseCloth;
    }

    public String getExpenseEducation() {
        return expenseEducation;
    }

    public void setExpenseEducation(String expenseEducation) {
        this.expenseEducation = expenseEducation;
    }

    public String getExpenseHealth() {
        return expenseHealth;
    }

    public void setExpenseHealth(String expenseHealth) {
        this.expenseHealth = expenseHealth;
    }

    public String getExpenseEntertain() {
        return expenseEntertain;
    }

    public void setExpenseEntertain(String expenseEntertain) {
        this.expenseEntertain = expenseEntertain;
    }

    public String getExpenseRent() {
        return expenseRent;
    }

    public void setExpenseRent(String expenseRent) {
        this.expenseRent = expenseRent;
    }

    public String getExpenseAgriculture() {
        return expenseAgriculture;
    }

    public void setExpenseAgriculture(String expenseAgriculture) {
        this.expenseAgriculture = expenseAgriculture;
    }

    public String getExpenseInstallment() {
        return expenseInstallment;
    }

    public void setExpenseInstallment(String expenseInstallment) {
        this.expenseInstallment = expenseInstallment;
    }

    public String getExpenseFuel() {
        return expenseFuel;
    }

    public void setExpenseFuel(String expenseFuel) {
        this.expenseFuel = expenseFuel;
    }

    public String getExpenseTranport() {
        return expenseTranport;
    }

    public void setExpenseTranport(String expenseTranport) {
        this.expenseTranport = expenseTranport;
    }

    public String getExpenseOthers() {
        return expenseOthers;
    }

    public void setExpenseOthers(String expenseOthers) {
        this.expenseOthers = expenseOthers;
    }
}