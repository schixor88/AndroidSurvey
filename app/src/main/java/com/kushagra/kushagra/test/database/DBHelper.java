package com.kushagra.kushagra.test.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.kushagra.kushagra.test.model.Details;
import com.kushagra.kushagra.test.model.Details2;
import com.kushagra.kushagra.test.model.FamilyMemberData;
import com.kushagra.kushagra.test.model.HouseholdData;
import com.kushagra.kushagra.test.model.User;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VER = 15;
    private static final String DATABASE_NAME = "KUSHAGRA";
    private static final String TABLE_NAME = "Details";

    //
    private static final String KEY_ID = "Id";
    //
    private static final String KEY_PRADESH = " Pradesh";
    private static final String KEY_JILLA = "Jilla";
    private static final String KEY_NAGARPALIKA = " Nagarpalika";
    private static final String KEY_WARD = " Ward";
    private static final String KEY_BASTI = "Basti";
    private static final String KEY_TOLENAME = "Tole";
    private static final String KEY_SADAKNAME = "Sadak";

    //
    private static final String KEY_LAT = "Latitude";   //achhansaw
    private static final String KEY_LNG = "Longitude";  //desantar
    private static final String KEY_ALT = "Altitude";

    //
    private static final String KEY_JATI = "Jati";
    private static final String KEY_VASA = "Vasa";
    private static final String KEY_DHARMA0 = "Dharma0";
    //
    private static final String KEY_GHARDHANINAME = "GhardhaniName";
    private static final String KEY_GHARDHANISEX = "GhardhaniSex";
    private static final String KEY_GHARDHANIPHONE = "GhardhaniPhone";

    //
    private static final String KEY_TENANT_FAMILY_COUNT = "tenant_family_count";
    private static final String KEY_INFORMER_NAME = "informer_name";
    private static final String KEY_INFORMER_GENDER = "informer_gender";
    private static final String KEY_INFORMER_AGE = "informer_age";
    private static final String KEY_INFORMER_EMAIL = "informer_email";
    private static final String KEY_SWAMITTO = "swamitto";
    private static final String KEY_BASAI_ABADHI = "basai_abadhi";
    private static final String KEY_JATIYA_SAMUHA = "jatiya_samuha";
    private static final String KEY_JATJATI = "jatjati";
    private static final String KEY_DHARMA = "dharma";
    private static final String KEY_MATRIBHASHA = "matribhasha";
    private static final String KEY_THAR = "thar";
    private static final String KEY_PARIWAR_BASOBAS_AVASTHA = "pariwar_basobas_avastha";
    private static final String KEY_BASAI_SARNU_KARAN = "basai_sarnu_karan";
    private static final String KEY_BASAI_AVADHI = "basai_avadhi";
    private static final String KEY_NAGARPALIKA_MA_GHAR_BHAYEKO = "nagarpalika_ma_ghar_bhayeko";
    private static final String KEY_GHAR_JAGGA_SWAMITTO_KISIM = "ghar_jagga_swamitto_kisim";
    private static final String KEY_NAGARPALIKA_MA_ANYA_GHAR_BHAYEKO = "nagarpalika_ma_anya_ghar_bhayeko";
    private static final String KEY_ANYA_GHAR_K_KO_LAGI = "anya_ghar_k_ko_lagi";
    private static final String KEY_KITCHEN_ALAGGAI_BHAYEKO = "kitchen_alaggai_bhayeko";
    private static final String KEY_KITCHEN_NIRMAN_AVASTHA = "kitchen_nirman_avastha";
    private static final String KEY_KITCHEN_NIRMAN_BARSA = "kitchen_nirman_barsa";
    private static final String KEY_BATTI_MAIN_SOURCE = "batti_main_source";
    private static final String KEY_ELECTRICITY_JADAN_BHAYEKO = "electricity_jadan_bhayeko";
    private static final String KEY_ELECTRICITY_JADAN_KINA_NABHAYEKO = "electricity_jadan_kina_nabhayeko";
    private static final String KEY_CULO_KO_PRAKAR = "chulo_ko_prakar";
    private static final String KEY_PAKAUNE_FUEL = "pakaune_fuel";
    private static final String KEY_DAAURA_KO_SOURCE = "daaura_ko_source";
    private static final String KEY_GAS_CYLINDER_TIKNE_DIN = "gas_cylinder_tikne_din";
    private static final String KEY_OLD_CHULO_KO_LAGI_DAAURA_KG = "old_chulo_ko_lagi_daaura_kg";
    private static final String KEY_MODERN_CHULO_KO_LAGI_DAAURA_KG = "modern_chulo_ko_lagi_daaura_kg";
    private static final String KEY_MATTITEL_PERMONTH_LITRE = "mattitel_permonth_litre";
    private static final String KEY_INDUCTION_HEATER_HOURSPERDAY = "induction_heater_hoursperday";
    private static final String KEY_OVEN_HOURSPERDAY = "oven_hoursperday";
    private static final String KEY_PAANI_TATAUNE_UPAKARAN = "paani_tataune_upakaran";
    private static final String KEY_PAANI_TATAUNA_CYLINDERPERYEAR = "paani_tatauna_cylinderperyear";
    private static final String KEY_PAANI_TATAUNA_WOOD_KGPERMONTH_OLD_CHULO = "paani_tatauna_wood_kgpermonth_old_chulo";
    private static final String KEY_PAANI_TATAUNA_WOOD_KGPERMONTH_MODERN = "paani_tatauna_wood_kgpermonth_modern";
    private static final String KEY_PAANI_TATAUNA_MATTITEL_PERMONTH = "paani_tatauna_mattitel_permonth";
    private static final String KEY_PAANI_TATAUNA_INDUCTION_HOURSPERDAY = "paani_tatauna_induction_hoursperday";
    private static final String KEY_PAANI_TATAUNA_OVEN_HOURSPERDAY = "paani_tatauna_oven_hoursperday";
    private static final String KEY_ROOM_HEATING_UPAKARAN = "room_heating_upakaran";
    private static final String KEY_ROOM_COOLING_UPAKARAN = "room_cooling_upakaran";
    private static final String KEY_FAN_COUNT = "fan_count";
    private static final String KEY_FAN_WATT = "fan_watt";
    private static final String KEY_FAN_HOURSPERDAY = "fan_hoursperday";
    private static final String KEY_AIRCONDITION_COUNT = "aircondition_count";
    private static final String KEY_AIRCONDITION_WATT = "aircondition_watt";
    private static final String KEY_AIRCONDITION_HOURSPERDAY = "aircondition_hoursperday";
    private static final String KEY_BIJULI_LOAD_AMPERE = "bijuli_load_ampere";
    private static final String KEY_BIJULI_UNIT = "bijuli_unit";
    private static final String KEY_WASHINGMACHINE_COUNT = "washingmachine_count";
    private static final String KEY_FRIDGE_COUNT = "fridge_count";
    private static final String KEY_SOLAR_COUNT = "solar_count";
    private static final String KEY_VACUUM_COUNT = "vacuum_count";
    private static final String KEY_INVERTER_COUNT = "inverter_count";
    private static final String KEY_GENERATOR_COUNT = "generator_count";
    private static final String KEY_DISHWASHER_COUNT = "dishwasher_count";
    private static final String KEY_CABLE_COUNT = "cable_count";
    private static final String KEY_OTHER_HOUSE_GADGET_COUNT = "other_house_gadget_count";
    private static final String KEY_RADIO_COUNT = "radio_count";
    private static final String KEY_TELEVISION_COUNT = "television_count";
    private static final String KEY_TELEPHONE_COUNT = "telephone_count";
    private static final String KEY_CELLPHONE_COUNT = "cellphone_count";
    private static final String KEY_SMARTPHONE_COUNT = "smartphone_count";
    private static final String KEY_COMPUTER_LAPTOP_COUNT = "computer_laptop_count";
    private static final String KEY_INTERNET_COUNT = "internet_count";
    private static final String KEY_OTHER_COMMUNICATION_COUNT = "other_communication_count";
    private static final String KEY_INTERNET_SPEED_MBPS = "internet_speed_mbps";
    private static final String KEY_CYCLE_COUNT = "cycle_count";
    private static final String KEY_BIKE_COUNT = "bike_count";
    private static final String KEY_LIGHTWEIGHT_VEHICLE_COUNT = "lightweight_vehicle_count";
    private static final String KEY_HEAVYWEIGHT_VEHICLE_COUNT = "heavyweight_vehicle_count";
    private static final String KEY_PUBLIC_TRANSPORT_VEHICLE_COUNT = "public_transport_vehicle_count";
    private static final String KEY_FAMILY_MA_YATAYAT_LICENSE_BHAYEKO = "family_ma_yatayat_license_bhayeko";
    private static final String KEY_LICENSE_BHAYEKO_COUNT = "license_bhayeko_count";
    private static final String KEY_LICENSE_COUNT_2_WHEELER = "license_count_2_wheeler";
    private static final String KEY_LICENSE_COUNT_4_WHEELER = "license_count_4_wheeler";
    private static final String KEY_BIRAMI_HUDA_KATA_LAGEKO = "one_year_ma_birami";
    private static final String KEY_ONE_YEAR_MA_BIRAMI = "birami_huda_kata_lageko";
    private static final String KEY_HEALTHPOST_JANA_HIDERA_TIME = "healthpost_jana_hidera_time";
    private static final String KEY_HEALTHPOST_JANA_GAADI_TIME = "healthpost_jana_gaadi_time";
    private static final String KEY_TWO_YEAR_LE_VACCINE_LAGAYEKO = "two_year_le_vaccine_lagayeko";
    private static final String KEY_VACCINE_NAGAREKO_KARAN = "vaccine_nagareko_karan";
    private static final String KEY_TWO_YEAR_MA_PREGNANT = "two_year_ma_pregnant";
    private static final String KEY_PREGNANT_KO_HEALTH_CHECKUP = "pregnant_ko_health_checkup";
    private static final String KEY_PREGNANT_KO_CHECK_NAGARNE_KARAN = "pregnant_ko_check_nagarne_karan";
    private static final String KEY_PREGNANT_KO_CHECK_GAREKO_COUNT = "pregnant_ko_check_gareko_count";
    private static final String KEY_PREGNANT_LE_ICON_PILL_LINEGAREKO = "pregnant_le_iron_pill_linegareko";
    private static final String KEY_PREGNANT_LE_JUKA_MEDICINE_LINEGAREKO = "pregnant_le_juka_medicine_linegareko";
    private static final String KEY_PREGNANT_LE_VITAMIN_LINEGAREKO = "pregnant_le_vitamin_linegareko";
    private static final String KEY_BACCHA_JANMAAUNE_STHAN = "baccha_janmaaune_sthan";
    private static final String KEY_BACCHA_JANMAAUNA_SAHAYOG_GARNE = "baccha_janmaauna_sahayog_garne";
    private static final String KEY_BACCHA_JANMAAUNA_HELP_NALINUKARAN = "baccha_janmaauna_help_nalinukaran";
    private static final String KEY_LAST12MONTHMA_UNDER_5YR_KO_DEATH = "last12Monthma_under_5yr_ko_death";
    private static final String KEY_UNDER_5YR_DEATH_GENDER = "under_5yr_death_gender";
    private static final String KEY_UNDER_5YR_DEATH_AGE = "under_5yr_death_age";
    private static final String KEY_UNDER_5YR_DEATH_KARAN = "under_5yr_death_karan";
    private static final String KEY_UNDER_5YR_KO_DEVELOPMENT_MEASURE_HUNCHA = "under_5yr_ko_development_measure_huncha";
    private static final String KEY_AGE6MONTH_TO_6YR_VITAMIN_A = "age6month_to_6yr_vitamin_A";
    private static final String KEY_AGE1YR_TO_6YR_JUKA_MEDICINE = "age1yr_to_6yr_juka_medicine";
    private static final String KEY_WINTER_MA_WARM_CLOTH = "winter_ma_warm_cloth";
    private static final String KEY_JHUUL_KO_BABYASTHA = "jhuul_ko_babyastha";
    private static final String KEY_REGULAR_HEALTH_CHECKUP_PERYEAR = "regular_health_checkup_peryear";
    private static final String KEY_PAST12MONTH_MA_DEATH_BHAYEKO = "past12month_ma_death_bhayeko";
    private static final String KEY_YOG_RA_ADHYATMIK_KENDRA_MA_JANEGAREKO = "yog_ra_adhyatmik_kendra_ma_janegareko";
    private static final String KEY_KHANEPAANI_SOURCE = "khanepaani_source";
    private static final String KEY_PAANI_METER_JADAN_GAREYEKO = "paani_meter_jadan_gareyeko";
    private static final String KEY_PAANI_SUFFICIENT_HUNE = "paani_sufficient_hune";
    private static final String KEY_PAANI_LINA_JANE_AAUNE_TIME = "paani_lina_jane_aaune_time";
    private static final String KEY_KHANEPAANI_QUALITY = "khanepaani_quality";
    private static final String KEY_PANI_PURIFY_GARNE_GAREKO = "pani_purify_garne_gareko";
    private static final String KEY_RAIN_WATER_COLLECT_GAREKO = "rain_water_collect_gareko";
    private static final String KEY_RAINWATER_KO_USE = "rainwater_ko_use";
    private static final String KEY_GHAAR_MA_DHAAL_ATTACH_BHAYEKO = "ghaar_ma_dhaal_attach_bhayeko";
    private static final String KEY_DHAAL_ATTACH_BHAYEKO_TYPE = "dhaal_attach_bhayeko_type";
    private static final String KEY_WASTE_WATER_MANAGEMENT = "waste_water_management";
    private static final String KEY_DECOMPOSE_NODECOMPOSE_SEPARATE_GARNE = "decompose_nodecompose_separate_garne";
    private static final String KEY_SOLID_WASTE_MANAGEMENT = "solid_waste_management";


    // Household Data
    private static final String KEY_HASTOILET = "hasToilet ";
    private static final String KEY_HASNOTOILET = "hasNoToilet ";
    private static final String KEY_TOILETTYPE = "toiletType ";
    private static final String KEY_TOILETWASTEMGMT = "toiletWasteMgmt ";
    private static final String KEY_ONLINESERVICES = "onlineServices ";
    private static final String KEY_PRIMARYINCOMESOURCE = "primaryIncomeSource ";
    private static final String KEY_OWNSLAND = "ownsLand ";
    private static final String KEY_OWNSNOLAND = "ownsNoLand ";
    private static final String KEY_LANDLOCATION = "landLocation ";
    private static final String KEY_LANDOWNEDBY = "landOwnedBy ";
    private static final String KEY_LANDUSEDAS = "landUsedAs ";
    private static final String KEY_LANDONLEASE = "landOnLease ";
    private static final String KEY_LANDONNOLEASE = "landOnNoLease ";
    private static final String KEY_LANDONLEASEUSEDAS = "landOnLeaseUsedAs ";
    private static final String KEY_PRIMARYAGROPRODUCTIONS = "primaryAgroProductions ";
    private static final String KEY_LASTYEARAGROPRODUCTIONS = "lastYearAgroProductions ";
    private static final String KEY_ASSETANIMALS = "assetAnimals ";
    private static final String KEY_ASSETBIRDS = "assetBirds ";
    private static final String KEY_ASSETSFISHES = "assetsFishes ";
    private static final String KEY_ASSETSBEES = "assetsBees ";
    private static final String KEY_HORTICULTUREPRODUCTIONCOUNTS = "horticultureProductionCounts ";
    private static final String KEY_SELLSPRODUCTIONS = "sellsProductions ";
    private static final String KEY_NOTSELLSPRODUCTIONS = "notSellsProductions ";
    private static final String KEY_SELLPRODUCTIONLIST = "sellProductionList ";
    private static final String KEY_SELLPRODUCTIONTO = "sellProductionTo ";
    private static final String KEY_HASFRUITPLANTS = "hasFruitPlants ";
    private static final String KEY_HASNOFRUITPLANTS = "hasNoFruitPlants ";
    private static final String KEY_HASVEGETABLECROPS = "hasVegetableCrops ";
    private static final String KEY_HASNOVEGETABLECROPS = "hasNoVegetableCrops ";
    private static final String KEY_LASTYEARINCOME = "lastYearIncome ";
    private static final String KEY_LASTYEAREXPENSE = "lastYearExpense ";
    private static final String KEY_WORKSABROAD = "worksAbroad ";
    private static final String KEY_NOTWORKABROAD = "notWorkAbroad ";
    private static final String KEY_REMITTANCESPENTON = "remittanceSpentOn ";
    private static final String KEY_LASTYEARINVESTMENTS = "lastYearInvestments ";
    private static final String KEY_PRODUCTIONSSUSTAINABLEFOR = "productionsSustainableFor ";
    private static final String KEY_INCOMEISSUFFICIENT = "incomeIsSufficient ";
    private static final String KEY_INCOMEISNOTSUFFICIENT = "incomeIsNotSufficient ";
    private static final String KEY_ADDITIONALCASHSOURCE = "additionalCashSource ";
    private static final String KEY_HASTAKENLOAN = "hasTakenLoan ";
    private static final String KEY_HASNOTTAKENLOAN = "hasNotTakenLoan ";
    private static final String KEY_LOANSOURCE = "loanSource ";
    private static final String KEY_TAKENLOANFOR = "takenLoanFor ";
    private static final String KEY_DURATIONTOCLEARLOAN = "durationToClearLoan ";
    private static final String KEY_KNOWSSAMHIT = "knowsSamhit ";
    private static final String KEY_NOTKNOWSAMHIT = "notKnowSamhit ";
    private static final String KEY_BUILTASPERSAMHIT = "builtAsPerSamhit ";
    private static final String KEY_NOTBUILTASPERSAMHIT = "notBuiltAsPerSamhit ";
    private static final String KEY_NOTKNOWBUILTASPERSAMHIT = "notKnowBuiltAsPerSamhit ";
    private static final String KEY_HASPERMISSIONBLUEPRINT = "hasPermissionBlueprint ";
    private static final String KEY_HASNOPERMISSIONBLUEPRINT = "hasNoPermissionBlueprint ";
    private static final String KEY_HASSAFEZONENEARHOUSE = "hasSafeZoneNearHouse ";
    private static final String KEY_HASNOSAFEZONENEARHOUSE = "hasNoSafeZoneNearHouse ";
    private static final String KEY_HOUSESUSCEPTTOCALAMITY = "houseSusceptToCalamity ";
    private static final String KEY_HOUSENOSUSCEPTTOCALAMITY = "houseNoSusceptToCalamity ";
    private static final String KEY_INFOABOUTEARTHQUAKE = "infoAboutEarthquake ";
    private static final String KEY_NOINFOABOUTEARTHQUAKE = "noInfoAboutEarthquake ";
    private static final String KEY_KNOWSAFEZONEINHOME = "knowSafeZoneInHome ";
    private static final String KEY_KNOWSNOSAFEZONEINHOME = "knowsNoSafeZoneInHome ";
    private static final String KEY_HASSUPPLIESFORSOE = "hasSuppliesForSOE ";
    private static final String KEY_HASNOSUPPLIESFORSOE = "hasNoSuppliesForSOE ";
    private static final String KEY_AFFECTEDBYCALAMITIESLIST = "affectedByCalamitiesList ";
    private static final String KEY_LASTMAJORDISASTER = "lastMajorDisaster ";
    private static final String KEY_HASASSETINSURANCE = "hasAssetInsurance ";
    private static final String KEY_HASNOASSETINSURANCE = "hasNoAssetInsurance ";
    private static final String KEY_ASSETSLISTWITHINSURANCE = "assetsListWithInsurance ";
    private static final String KEY_STEPSTAKENTOMINIMIZEDISASTEREFFECTS = "stepsTakenToMinimizeDisasterEffects ";
    private static final String KEY_ISVIOLENCEVICTIM = "isViolenceVictim ";
    private static final String KEY_ISNOTVIOLENCEVICTIM = "isNotViolenceVictim ";
    private static final String KEY_DEATHCOUNT = "deathCount ";
    private static final String KEY_INJURYCOUNT = "injuryCount ";
    private static final String KEY_MISSINGCOUNT = "missingCount ";
    private static final String KEY_ASSETLOSSAMOUNTRS = "assetLossAmountRs ";
    private static final String KEY_ISSUED = "isSued ";
    private static final String KEY_ISNOTSUED = "isNotSued ";
    private static final String KEY_ISSHIFTED = "isShifted ";
    private static final String KEY_ISNOTSHIFTED = "isNotShifted ";
    private static final String KEY_ISSEXUALLYABUSE = "isSexuallyAbuse ";
    private static final String KEY_ISNOTSEXUALLYABUSED = "isNotSexuallyAbused ";
    private static final String KEY_MEMBERMISSING = "memberMissing ";
    private static final String KEY_MEMBERNOTMISSING = "memberNotMissing ";
    private static final String KEY_FEELSSAFEINMUNICIPAL = "feelsSafeInMunicipal ";
    private static final String KEY_FEELSNOSAFEINMUNICIPAL = "feelsNoSafeInMunicipal ";
    private static final String KEY_REASONFEELSSAFE = "reasonFeelsSafe ";
    private static final String KEY_REASONNOFEELSAFE = "reasonNoFeelSafe ";
    private static final String KEY_WARDNOWHERENOTFEELSAFE = "wardNoWhereNotFeelSafe ";
    private static final String KEY_REGIONNAMEWHERENOTFEELSAFE = "regionNameWhereNotFeelSafe ";
    private static final String KEY_HOMEMEMBERUPTOAGE16WORKS = "homeMemberUptoAge16Works ";
    private static final String KEY_NOHOMEMEMBERUPTOAGE16WORKS = "noHomeMemberUptoAge16Works ";
    private static final String KEY_UPTOAGE16WORINGBOYCOUNT = "uptoAge16WoringBoyCount ";
    private static final String KEY_UPTOAGE16WORKINGGIRLCOUNT = "uptoAge16WorkingGirlCount ";
    private static final String KEY_MEMBERUPTOAGE16HIREDWORK = "memberUptoAge16HiredWork ";
    private static final String KEY_NOMEMBERUPTOAGE16HIREDWORK = "noMemberUptoAge16HiredWork ";
    private static final String KEY_UPTOAGE16HIREDBOYCOUNT = "uptoAge16HiredBoyCount ";
    private static final String KEY_UPTOAGE16HIREDGIRLCOUNT = "uptoAge16HiredGirlCount ";
    private static final String KEY_CHILDRENISONBADINFLUENCE = "childrenIsOnBadInfluence ";
    private static final String KEY_CHILDRENNOTONBADINFLUENCE = "childrenNotOnBadInfluence ";
    private static final String KEY_CHILDRENBADINFLUENCEON = "childrenBadInfluenceOn ";
    private static final String KEY_WAYOFMAKINGFAMILYDECISION = "wayOfMakingFamilyDecision ";
    private static final String KEY_ASSETSONFEMALENAME = "assetsOnFemaleName ";
    private static final String KEY_ALLOWANCELISTTAKENBYFAMILY = "allowanceListTakenByFamily ";
    private static final String KEY_MEMBERHASINVOLVEDONDEVELOPMENT = "memberHasInvolvedOnDevelopment ";
    private static final String KEY_MEMBERHASNOTINVOLVEDONDEVELOPMENT = "memberHasNotInvolvedOnDevelopment ";
    private static final String KEY_OPINIONONDEVELOPMENTPRIORITY = "opinionOnDevelopmentPriority ";
    private static final String KEY_ANNABALI_MURI = "annabali_muri ";
    private static final String KEY_DHAN_MURI = "dhan_muri ";
    private static final String KEY_MAKAI_MURI = "makai_muri ";
    private static final String KEY_KODO_MURI = "kodo_muri ";
    private static final String KEY_GHAU_MURI = "ghau_muri ";
    private static final String KEY_FAPAR_MURI = "fapar_muri ";
    private static final String KEY_OTHERS_MURI = "others_muri ";
    private static final String KEY_OIL_KG = "oil_kg ";
    private static final String KEY_DAAL_KG = "daal_kg ";
    private static final String KEY_TARKARI_KG = "tarkari_kg ";
    private static final String KEY_FRESHTARKARI_KG = "freshTarkari_kg ";
    private static final String KEY_AALU_KG = "aalu_kg ";
    private static final String KEY_MASALA_KG = "masala_kg ";
    private static final String KEY_FALFUL_KG = "falful_kg ";
    private static final String KEY_KANDAMUL_KG = "kandamul_kg ";
    private static final String KEY_OTHERS_KG = "others_kg ";
    private static final String KEY_COW_STHANIYA = "cow_sthaniya ";
    private static final String KEY_COW_UNNAT = "cow_unnat ";
    private static final String KEY_BUFFALO_STHANIYA = "buffalo_sthaniya ";
    private static final String KEY_BUFFALO_UNNAT = "buffalo_unnat ";
    private static final String KEY_GOAT_STHANIYA = "goat_sthaniya ";
    private static final String KEY_GOAT_UNNAT = "goat_unnat ";
    private static final String KEY_SHEEP_STHANIYA = "sheep_sthaniya ";
    private static final String KEY_SHEEP_UNNAT = "sheep_unnat ";
    private static final String KEY_PIG_STHANIYA = "pig_sthaniya ";
    private static final String KEY_PIG_UNNAT = "pig_unnat ";
    private static final String KEY_OTHER_ANIMAL = "other_animal ";
    private static final String KEY_HEN_STHANIYA = "hen_sthaniya ";
    private static final String KEY_HEN_UNNAT = "hen_unnat ";
    private static final String KEY_PIGEON_COUNT = "pigeon_count ";
    private static final String KEY_OTHER_BIRDS = "other_birds ";
    private static final String KEY_FISH_COUNT = "fish_count ";
    private static final String KEY_BEEHIVE_COUNT = "beehive_count ";
    private static final String KEY_OTHER_PASUPANCHI = "other_pasupanchi ";
    private static final String KEY_MILKCURD_LITRE = "milkCurd_litre ";
    private static final String KEY_GHEE_KG = "ghee_kg ";
    private static final String KEY_OTHERDAIRY_KG = "otherDairy_kg ";
    private static final String KEY_MEAT_KG = "meat_kg ";
    private static final String KEY_COMPOST_QUINTAL = "compost_quintal ";
    private static final String KEY_URINE_LITRE = "urine_litre ";
    private static final String KEY_WOOL_KG = "wool_kg ";
    private static final String KEY_EGG_CRATE = "egg_crate ";
    private static final String KEY_FISH_KG = "fish_kg ";
    private static final String KEY_HONEY_KG = "honey_kg ";
    private static final String KEY_OTHER_PRODUCTION_KG = "other_production_kg ";
    private static final String KEY_INCOMEAGRICULTURE = "incomeAgriculture ";
    private static final String KEY_INCOMEBUSINESS = "incomeBusiness ";
    private static final String KEY_INCOMESALARYPENSION = "incomeSalaryPension ";
    private static final String KEY_INCOMESOCIALALLOWANCE = "incomeSocialAllowance ";
    private static final String KEY_INCOMEFOREIGNEMP = "incomeForeignEmp ";
    private static final String KEY_INCOMEWAGES = "incomeWages ";
    private static final String KEY_INCOMERENT = "incomeRent ";
    private static final String KEY_INCOMEINTERESTINVEST = "incomeInterestInvest ";
    private static final String KEY_INCOMEOTHERS = "incomeOthers ";
    private static final String KEY_EXPENSEFOOD = "expenseFood ";
    private static final String KEY_EXPENSECLOTH = "expenseCloth ";
    private static final String KEY_EXPENSEEDUCATION = "expenseEducation ";
    private static final String KEY_EXPENSEHEALTH = "expenseHealth ";
    private static final String KEY_EXPENSEENTERTAIN = "expenseEntertain ";
    private static final String KEY_EXPENSERENT = "expenseRent ";
    private static final String KEY_EXPENSEAGRICULTURE = "expenseAgriculture ";
    private static final String KEY_EXPENSEINSTALLMENT = "expenseInstallment ";
    private static final String KEY_EXPENSEFUEL = "expenseFuel ";
    private static final String KEY_EXPENSETRANPORT = "expenseTranport ";
    private static final String KEY_EXPENSEOTHERS = "expenseOthers ";

    // Individual Data
    private static final String KEY_MEMBER_0_NAME = "Member_0_name ";
    private static final String KEY_MEMBER_0_CAST = "Member_0_cast ";
    private static final String KEY_MEMBER_0_GENDER = "Member_0_gender ";
    private static final String KEY_MEMBER_0_AGE = "Member_0_age ";
    private static final String KEY_MEMBER_0_RELATIONTOOWNER = "Member_0_relationToOwner ";
    private static final String KEY_MEMBER_0_BIRTHPLACE = "Member_0_birthplace ";
    private static final String KEY_MEMBER_0_HASEMAIL = "Member_0_hasEmail ";
    private static final String KEY_MEMBER_0_HASNOEMAIL = "Member_0_hasNoEmail ";
    private static final String KEY_MEMBER_0_HASLEFTHOME6MONTH = "Member_0_hasLeftHome6Month ";
    private static final String KEY_MEMBER_0_HASNOTLEFTHOME6MONTH = "Member_0_hasNotLeftHome6Month ";
    private static final String KEY_MEMBER_0_LEAVEHOME_PLACE = "Member_0_leaveHome_place ";
    private static final String KEY_MEMBER_0_LEAVEHOME_REASON = "Member_0_leaveHome_reason ";
    private static final String KEY_MEMBER_0_EDUCATION = "Member_0_education ";
    private static final String KEY_MEMBER_0_SCHOOLTYPE = "Member_0_schoolType ";
    private static final String KEY_MEMBER_0_LEAVESCHOOL_REASON = "Member_0_leaveSchool_reason ";
    private static final String KEY_MEMBER_0_SCHOOLLEVEL = "Member_0_schoolLevel ";
    private static final String KEY_MEMBER_0_INCOMESOURCE = "Member_0_incomeSource ";
    private static final String KEY_MEMBER_0_ABROAD_COUNTRY = "Member_0_abroad_country ";
    private static final String KEY_MEMBER_0_ABROAD_MONEYTRANSFER = "Member_0_abroad_moneyTransfer ";
    private static final String KEY_MEMBER_0_SKILLS = "Member_0_skills ";
    private static final String KEY_MEMBER_0_HASBANKAC = "Member_0_hasBankAC ";
    private static final String KEY_MEMBER_0_HASNOBANKAC = "Member_0_hasNoBankAC ";
    private static final String KEY_MEMBER_0_HASATM = "Member_0_hasATM ";
    private static final String KEY_MEMBER_0_HASNOATM = "Member_0_hasNoATM ";
    private static final String KEY_MEMBER_0_USEONLINEBANKING = "Member_0_useOnlineBanking ";
    private static final String KEY_MEMBER_0_NOTUSEONLINEBANKING = "Member_0_notUseOnlineBanking ";
    private static final String KEY_MEMBER_0_REGULARDEPOSIT = "Member_0_regularDeposit ";
    private static final String KEY_MEMBER_0_NOTREGULARDEPOSIT = "Member_0_notRegularDeposit ";
    private static final String KEY_MEMBER_0_REGULARDEPOSIT_TO = "Member_0_regularDeposit_to ";
    private static final String KEY_MEMBER_0_ISHEALTHY = "Member_0_isHealthy ";
    private static final String KEY_MEMBER_0_ISDISABLED = "Member_0_isDisabled ";
    private static final String KEY_MEMBER_0_DISABILITYTYPE = "Member_0_disabilityType ";
    private static final String KEY_MEMBER_0_HASDISABILITYCARD = "Member_0_hasDisabilityCard ";
    private static final String KEY_MEMBER_0_HASNODISABILITYCARD = "Member_0_hasNoDisabilityCard ";
    private static final String KEY_MEMBER_0_HASDISEASE12MONTH = "Member_0_hasDisease12Month ";
    private static final String KEY_MEMBER_0_NODISEASE12MONTH = "Member_0_noDisease12Month ";
    private static final String KEY_MEMBER_0_HASLONGTERMDISEASE = "Member_0_hasLongTermDisease ";
    private static final String KEY_MEMBER_0_NOLONGTERMDISEASE = "Member_0_noLongTermDisease ";
    private static final String KEY_MEMBER_0_LONGTERMDISEASENAME = "Member_0_longTermDiseaseName ";
    private static final String KEY_MEMBER_0_HASCOMMUNICABLEDISEASE = "Member_0_hasCommunicableDisease ";
    private static final String KEY_MEMBER_0_NOCOMMUNICABLEDISEASE = "Member_0_noCommunicableDisease ";
    private static final String KEY_MEMBER_0_COMMUNICABLEDISEASENAME = "Member_0_communicableDiseaseName ";
    private static final String KEY_MEMBER_0_USESVACCINE = "Member_0_usesVaccine ";
    private static final String KEY_MEMBER_0_SOCIALIDENTITY = "Member_0_socialIdentity ";
    private static final String KEY_MEMBER_0_SOCIALSECURITY_TYPE = "Member_0_socialSecurity_type ";
    private static final String KEY_MEMBER_0_MARITALSTATUS = "Member_0_maritalStatus ";
    private static final String KEY_MEMBER_0_SOCIALINVOLVEMENTS = "Member_0_socialInvolvements ";
    private static final String KEY_MEMBER_0_HASRECEIVEDTRAINING = "Member_0_hasReceivedTraining ";
    private static final String KEY_MEMBER_0_NOTRECEIVEDTRAINING = "Member_0_notReceivedTraining ";
    private static final String KEY_MEMBER_0_TRAININGLIST = "Member_0_trainingList ";
    private static final String KEY_MEMBER_0_ISPOLITICALINFLUENCER = "Member_0_isPoliticalInfluencer ";
    private static final String KEY_MEMBER_0_ISNOTPOLITICALINFLUENCER = "Member_0_isNotPoliticalInfluencer ";
    private static final String KEY_MEMBER_0_POLITICALINFLUENCERLEVEL = "Member_0_politicalInfluencerLevel ";
    private static final String KEY_MEMBER_0_TRAVELWORK = "Member_0_travelWork ";
    private static final String KEY_MEMBER_0_TRAVELBUSINESS = "Member_0_travelBusiness ";
    private static final String KEY_MEMBER_0_TRAVELEDUCATION = "Member_0_travelEducation ";
    private static final String KEY_MEMBER_0_TRAVELOTHERS = "Member_0_travelOthers ";

    private static final String KEY_MEMBER_1_NAME = "Member_1_name ";
    private static final String KEY_MEMBER_1_CAST = "Member_1_cast ";
    private static final String KEY_MEMBER_1_GENDER = "Member_1_gender ";
    private static final String KEY_MEMBER_1_AGE = "Member_1_age ";
    private static final String KEY_MEMBER_1_RELATIONTOOWNER = "Member_1_relationToOwner ";
    private static final String KEY_MEMBER_1_BIRTHPLACE = "Member_1_birthplace ";
    private static final String KEY_MEMBER_1_HASEMAIL = "Member_1_hasEmail ";
    private static final String KEY_MEMBER_1_HASNOEMAIL = "Member_1_hasNoEmail ";
    private static final String KEY_MEMBER_1_HASLEFTHOME6MONTH = "Member_1_hasLeftHome6Month ";
    private static final String KEY_MEMBER_1_HASNOTLEFTHOME6MONTH = "Member_1_hasNotLeftHome6Month ";
    private static final String KEY_MEMBER_1_LEAVEHOME_PLACE = "Member_1_leaveHome_place ";
    private static final String KEY_MEMBER_1_LEAVEHOME_REASON = "Member_1_leaveHome_reason ";
    private static final String KEY_MEMBER_1_EDUCATION = "Member_1_education ";
    private static final String KEY_MEMBER_1_SCHOOLTYPE = "Member_1_schoolType ";
    private static final String KEY_MEMBER_1_LEAVESCHOOL_REASON = "Member_1_leaveSchool_reason ";
    private static final String KEY_MEMBER_1_SCHOOLLEVEL = "Member_1_schoolLevel ";
    private static final String KEY_MEMBER_1_INCOMESOURCE = "Member_1_incomeSource ";
    private static final String KEY_MEMBER_1_ABROAD_COUNTRY = "Member_1_abroad_country ";
    private static final String KEY_MEMBER_1_ABROAD_MONEYTRANSFER = "Member_1_abroad_moneyTransfer ";
    private static final String KEY_MEMBER_1_SKILLS = "Member_1_skills ";
    private static final String KEY_MEMBER_1_HASBANKAC = "Member_1_hasBankAC ";
    private static final String KEY_MEMBER_1_HASNOBANKAC = "Member_1_hasNoBankAC ";
    private static final String KEY_MEMBER_1_HASATM = "Member_1_hasATM ";
    private static final String KEY_MEMBER_1_HASNOATM = "Member_1_hasNoATM ";
    private static final String KEY_MEMBER_1_USEONLINEBANKING = "Member_1_useOnlineBanking ";
    private static final String KEY_MEMBER_1_NOTUSEONLINEBANKING = "Member_1_notUseOnlineBanking ";
    private static final String KEY_MEMBER_1_REGULARDEPOSIT = "Member_1_regularDeposit ";
    private static final String KEY_MEMBER_1_NOTREGULARDEPOSIT = "Member_1_notRegularDeposit ";
    private static final String KEY_MEMBER_1_REGULARDEPOSIT_TO = "Member_1_regularDeposit_to ";
    private static final String KEY_MEMBER_1_ISHEALTHY = "Member_1_isHealthy ";
    private static final String KEY_MEMBER_1_ISDISABLED = "Member_1_isDisabled ";
    private static final String KEY_MEMBER_1_DISABILITYTYPE = "Member_1_disabilityType ";
    private static final String KEY_MEMBER_1_HASDISABILITYCARD = "Member_1_hasDisabilityCard ";
    private static final String KEY_MEMBER_1_HASNODISABILITYCARD = "Member_1_hasNoDisabilityCard ";
    private static final String KEY_MEMBER_1_HASDISEASE12MONTH = "Member_1_hasDisease12Month ";
    private static final String KEY_MEMBER_1_NODISEASE12MONTH = "Member_1_noDisease12Month ";
    private static final String KEY_MEMBER_1_HASLONGTERMDISEASE = "Member_1_hasLongTermDisease ";
    private static final String KEY_MEMBER_1_NOLONGTERMDISEASE = "Member_1_noLongTermDisease ";
    private static final String KEY_MEMBER_1_LONGTERMDISEASENAME = "Member_1_longTermDiseaseName ";
    private static final String KEY_MEMBER_1_HASCOMMUNICABLEDISEASE = "Member_1_hasCommunicableDisease ";
    private static final String KEY_MEMBER_1_NOCOMMUNICABLEDISEASE = "Member_1_noCommunicableDisease ";
    private static final String KEY_MEMBER_1_COMMUNICABLEDISEASENAME = "Member_1_communicableDiseaseName ";
    private static final String KEY_MEMBER_1_USESVACCINE = "Member_1_usesVaccine ";
    private static final String KEY_MEMBER_1_SOCIALIDENTITY = "Member_1_socialIdentity ";
    private static final String KEY_MEMBER_1_SOCIALSECURITY_TYPE = "Member_1_socialSecurity_type ";
    private static final String KEY_MEMBER_1_MARITALSTATUS = "Member_1_maritalStatus ";
    private static final String KEY_MEMBER_1_SOCIALINVOLVEMENTS = "Member_1_socialInvolvements ";
    private static final String KEY_MEMBER_1_HASRECEIVEDTRAINING = "Member_1_hasReceivedTraining ";
    private static final String KEY_MEMBER_1_NOTRECEIVEDTRAINING = "Member_1_notReceivedTraining ";
    private static final String KEY_MEMBER_1_TRAININGLIST = "Member_1_trainingList ";
    private static final String KEY_MEMBER_1_ISPOLITICALINFLUENCER = "Member_1_isPoliticalInfluencer ";
    private static final String KEY_MEMBER_1_ISNOTPOLITICALINFLUENCER = "Member_1_isNotPoliticalInfluencer ";
    private static final String KEY_MEMBER_1_POLITICALINFLUENCERLEVEL = "Member_1_politicalInfluencerLevel ";
    private static final String KEY_MEMBER_1_TRAVELWORK = "Member_1_travelWork ";
    private static final String KEY_MEMBER_1_TRAVELBUSINESS = "Member_1_travelBusiness ";
    private static final String KEY_MEMBER_1_TRAVELEDUCATION = "Member_1_travelEducation ";
    private static final String KEY_MEMBER_1_TRAVELOTHERS = "Member_1_travelOthers ";

    private static final String KEY_MEMBER_2_NAME = "Member_2_name ";
    private static final String KEY_MEMBER_2_CAST = "Member_2_cast ";
    private static final String KEY_MEMBER_2_GENDER = "Member_2_gender ";
    private static final String KEY_MEMBER_2_AGE = "Member_2_age ";
    private static final String KEY_MEMBER_2_RELATIONTOOWNER = "Member_2_relationToOwner ";
    private static final String KEY_MEMBER_2_BIRTHPLACE = "Member_2_birthplace ";
    private static final String KEY_MEMBER_2_HASEMAIL = "Member_2_hasEmail ";
    private static final String KEY_MEMBER_2_HASNOEMAIL = "Member_2_hasNoEmail ";
    private static final String KEY_MEMBER_2_HASLEFTHOME6MONTH = "Member_2_hasLeftHome6Month ";
    private static final String KEY_MEMBER_2_HASNOTLEFTHOME6MONTH = "Member_2_hasNotLeftHome6Month ";
    private static final String KEY_MEMBER_2_LEAVEHOME_PLACE = "Member_2_leaveHome_place ";
    private static final String KEY_MEMBER_2_LEAVEHOME_REASON = "Member_2_leaveHome_reason ";
    private static final String KEY_MEMBER_2_EDUCATION = "Member_2_education ";
    private static final String KEY_MEMBER_2_SCHOOLTYPE = "Member_2_schoolType ";
    private static final String KEY_MEMBER_2_LEAVESCHOOL_REASON = "Member_2_leaveSchool_reason ";
    private static final String KEY_MEMBER_2_SCHOOLLEVEL = "Member_2_schoolLevel ";
    private static final String KEY_MEMBER_2_INCOMESOURCE = "Member_2_incomeSource ";
    private static final String KEY_MEMBER_2_ABROAD_COUNTRY = "Member_2_abroad_country ";
    private static final String KEY_MEMBER_2_ABROAD_MONEYTRANSFER = "Member_2_abroad_moneyTransfer ";
    private static final String KEY_MEMBER_2_SKILLS = "Member_2_skills ";
    private static final String KEY_MEMBER_2_HASBANKAC = "Member_2_hasBankAC ";
    private static final String KEY_MEMBER_2_HASNOBANKAC = "Member_2_hasNoBankAC ";
    private static final String KEY_MEMBER_2_HASATM = "Member_2_hasATM ";
    private static final String KEY_MEMBER_2_HASNOATM = "Member_2_hasNoATM ";
    private static final String KEY_MEMBER_2_USEONLINEBANKING = "Member_2_useOnlineBanking ";
    private static final String KEY_MEMBER_2_NOTUSEONLINEBANKING = "Member_2_notUseOnlineBanking ";
    private static final String KEY_MEMBER_2_REGULARDEPOSIT = "Member_2_regularDeposit ";
    private static final String KEY_MEMBER_2_NOTREGULARDEPOSIT = "Member_2_notRegularDeposit ";
    private static final String KEY_MEMBER_2_REGULARDEPOSIT_TO = "Member_2_regularDeposit_to ";
    private static final String KEY_MEMBER_2_ISHEALTHY = "Member_2_isHealthy ";
    private static final String KEY_MEMBER_2_ISDISABLED = "Member_2_isDisabled ";
    private static final String KEY_MEMBER_2_DISABILITYTYPE = "Member_2_disabilityType ";
    private static final String KEY_MEMBER_2_HASDISABILITYCARD = "Member_2_hasDisabilityCard ";
    private static final String KEY_MEMBER_2_HASNODISABILITYCARD = "Member_2_hasNoDisabilityCard ";
    private static final String KEY_MEMBER_2_HASDISEASE12MONTH = "Member_2_hasDisease12Month ";
    private static final String KEY_MEMBER_2_NODISEASE12MONTH = "Member_2_noDisease12Month ";
    private static final String KEY_MEMBER_2_HASLONGTERMDISEASE = "Member_2_hasLongTermDisease ";
    private static final String KEY_MEMBER_2_NOLONGTERMDISEASE = "Member_2_noLongTermDisease ";
    private static final String KEY_MEMBER_2_LONGTERMDISEASENAME = "Member_2_longTermDiseaseName ";
    private static final String KEY_MEMBER_2_HASCOMMUNICABLEDISEASE = "Member_2_hasCommunicableDisease ";
    private static final String KEY_MEMBER_2_NOCOMMUNICABLEDISEASE = "Member_2_noCommunicableDisease ";
    private static final String KEY_MEMBER_2_COMMUNICABLEDISEASENAME = "Member_2_communicableDiseaseName ";
    private static final String KEY_MEMBER_2_USESVACCINE = "Member_2_usesVaccine ";
    private static final String KEY_MEMBER_2_SOCIALIDENTITY = "Member_2_socialIdentity ";
    private static final String KEY_MEMBER_2_SOCIALSECURITY_TYPE = "Member_2_socialSecurity_type ";
    private static final String KEY_MEMBER_2_MARITALSTATUS = "Member_2_maritalStatus ";
    private static final String KEY_MEMBER_2_SOCIALINVOLVEMENTS = "Member_2_socialInvolvements ";
    private static final String KEY_MEMBER_2_HASRECEIVEDTRAINING = "Member_2_hasReceivedTraining ";
    private static final String KEY_MEMBER_2_NOTRECEIVEDTRAINING = "Member_2_notReceivedTraining ";
    private static final String KEY_MEMBER_2_TRAININGLIST = "Member_2_trainingList ";
    private static final String KEY_MEMBER_2_ISPOLITICALINFLUENCER = "Member_2_isPoliticalInfluencer ";
    private static final String KEY_MEMBER_2_ISNOTPOLITICALINFLUENCER = "Member_2_isNotPoliticalInfluencer ";
    private static final String KEY_MEMBER_2_POLITICALINFLUENCERLEVEL = "Member_2_politicalInfluencerLevel ";
    private static final String KEY_MEMBER_2_TRAVELWORK = "Member_2_travelWork ";
    private static final String KEY_MEMBER_2_TRAVELBUSINESS = "Member_2_travelBusiness ";
    private static final String KEY_MEMBER_2_TRAVELEDUCATION = "Member_2_travelEducation ";
    private static final String KEY_MEMBER_2_TRAVELOTHERS = "Member_2_travelOthers ";

    private static final String KEY_MEMBER_3_NAME = "Member_3_name ";
    private static final String KEY_MEMBER_3_CAST = "Member_3_cast ";
    private static final String KEY_MEMBER_3_GENDER = "Member_3_gender ";
    private static final String KEY_MEMBER_3_AGE = "Member_3_age ";
    private static final String KEY_MEMBER_3_RELATIONTOOWNER = "Member_3_relationToOwner ";
    private static final String KEY_MEMBER_3_BIRTHPLACE = "Member_3_birthplace ";
    private static final String KEY_MEMBER_3_HASEMAIL = "Member_3_hasEmail ";
    private static final String KEY_MEMBER_3_HASNOEMAIL = "Member_3_hasNoEmail ";
    private static final String KEY_MEMBER_3_HASLEFTHOME6MONTH = "Member_3_hasLeftHome6Month ";
    private static final String KEY_MEMBER_3_HASNOTLEFTHOME6MONTH = "Member_3_hasNotLeftHome6Month ";
    private static final String KEY_MEMBER_3_LEAVEHOME_PLACE = "Member_3_leaveHome_place ";
    private static final String KEY_MEMBER_3_LEAVEHOME_REASON = "Member_3_leaveHome_reason ";
    private static final String KEY_MEMBER_3_EDUCATION = "Member_3_education ";
    private static final String KEY_MEMBER_3_SCHOOLTYPE = "Member_3_schoolType ";
    private static final String KEY_MEMBER_3_LEAVESCHOOL_REASON = "Member_3_leaveSchool_reason ";
    private static final String KEY_MEMBER_3_SCHOOLLEVEL = "Member_3_schoolLevel ";
    private static final String KEY_MEMBER_3_INCOMESOURCE = "Member_3_incomeSource ";
    private static final String KEY_MEMBER_3_ABROAD_COUNTRY = "Member_3_abroad_country ";
    private static final String KEY_MEMBER_3_ABROAD_MONEYTRANSFER = "Member_3_abroad_moneyTransfer ";
    private static final String KEY_MEMBER_3_SKILLS = "Member_3_skills ";
    private static final String KEY_MEMBER_3_HASBANKAC = "Member_3_hasBankAC ";
    private static final String KEY_MEMBER_3_HASNOBANKAC = "Member_3_hasNoBankAC ";
    private static final String KEY_MEMBER_3_HASATM = "Member_3_hasATM ";
    private static final String KEY_MEMBER_3_HASNOATM = "Member_3_hasNoATM ";
    private static final String KEY_MEMBER_3_USEONLINEBANKING = "Member_3_useOnlineBanking ";
    private static final String KEY_MEMBER_3_NOTUSEONLINEBANKING = "Member_3_notUseOnlineBanking ";
    private static final String KEY_MEMBER_3_REGULARDEPOSIT = "Member_3_regularDeposit ";
    private static final String KEY_MEMBER_3_NOTREGULARDEPOSIT = "Member_3_notRegularDeposit ";
    private static final String KEY_MEMBER_3_REGULARDEPOSIT_TO = "Member_3_regularDeposit_to ";
    private static final String KEY_MEMBER_3_ISHEALTHY = "Member_3_isHealthy ";
    private static final String KEY_MEMBER_3_ISDISABLED = "Member_3_isDisabled ";
    private static final String KEY_MEMBER_3_DISABILITYTYPE = "Member_3_disabilityType ";
    private static final String KEY_MEMBER_3_HASDISABILITYCARD = "Member_3_hasDisabilityCard ";
    private static final String KEY_MEMBER_3_HASNODISABILITYCARD = "Member_3_hasNoDisabilityCard ";
    private static final String KEY_MEMBER_3_HASDISEASE12MONTH = "Member_3_hasDisease12Month ";
    private static final String KEY_MEMBER_3_NODISEASE12MONTH = "Member_3_noDisease12Month ";
    private static final String KEY_MEMBER_3_HASLONGTERMDISEASE = "Member_3_hasLongTermDisease ";
    private static final String KEY_MEMBER_3_NOLONGTERMDISEASE = "Member_3_noLongTermDisease ";
    private static final String KEY_MEMBER_3_LONGTERMDISEASENAME = "Member_3_longTermDiseaseName ";
    private static final String KEY_MEMBER_3_HASCOMMUNICABLEDISEASE = "Member_3_hasCommunicableDisease ";
    private static final String KEY_MEMBER_3_NOCOMMUNICABLEDISEASE = "Member_3_noCommunicableDisease ";
    private static final String KEY_MEMBER_3_COMMUNICABLEDISEASENAME = "Member_3_communicableDiseaseName ";
    private static final String KEY_MEMBER_3_USESVACCINE = "Member_3_usesVaccine ";
    private static final String KEY_MEMBER_3_SOCIALIDENTITY = "Member_3_socialIdentity ";
    private static final String KEY_MEMBER_3_SOCIALSECURITY_TYPE = "Member_3_socialSecurity_type ";
    private static final String KEY_MEMBER_3_MARITALSTATUS = "Member_3_maritalStatus ";
    private static final String KEY_MEMBER_3_SOCIALINVOLVEMENTS = "Member_3_socialInvolvements ";
    private static final String KEY_MEMBER_3_HASRECEIVEDTRAINING = "Member_3_hasReceivedTraining ";
    private static final String KEY_MEMBER_3_NOTRECEIVEDTRAINING = "Member_3_notReceivedTraining ";
    private static final String KEY_MEMBER_3_TRAININGLIST = "Member_3_trainingList ";
    private static final String KEY_MEMBER_3_ISPOLITICALINFLUENCER = "Member_3_isPoliticalInfluencer ";
    private static final String KEY_MEMBER_3_ISNOTPOLITICALINFLUENCER = "Member_3_isNotPoliticalInfluencer ";
    private static final String KEY_MEMBER_3_POLITICALINFLUENCERLEVEL = "Member_3_politicalInfluencerLevel ";
    private static final String KEY_MEMBER_3_TRAVELWORK = "Member_3_travelWork ";
    private static final String KEY_MEMBER_3_TRAVELBUSINESS = "Member_3_travelBusiness ";
    private static final String KEY_MEMBER_3_TRAVELEDUCATION = "Member_3_travelEducation ";
    private static final String KEY_MEMBER_3_TRAVELOTHERS = "Member_3_travelOthers ";

    private static final String KEY_MEMBER_4_NAME = "Member_4_name ";
    private static final String KEY_MEMBER_4_CAST = "Member_4_cast ";
    private static final String KEY_MEMBER_4_GENDER = "Member_4_gender ";
    private static final String KEY_MEMBER_4_AGE = "Member_4_age ";
    private static final String KEY_MEMBER_4_RELATIONTOOWNER = "Member_4_relationToOwner ";
    private static final String KEY_MEMBER_4_BIRTHPLACE = "Member_4_birthplace ";
    private static final String KEY_MEMBER_4_HASEMAIL = "Member_4_hasEmail ";
    private static final String KEY_MEMBER_4_HASNOEMAIL = "Member_4_hasNoEmail ";
    private static final String KEY_MEMBER_4_HASLEFTHOME6MONTH = "Member_4_hasLeftHome6Month ";
    private static final String KEY_MEMBER_4_HASNOTLEFTHOME6MONTH = "Member_4_hasNotLeftHome6Month ";
    private static final String KEY_MEMBER_4_LEAVEHOME_PLACE = "Member_4_leaveHome_place ";
    private static final String KEY_MEMBER_4_LEAVEHOME_REASON = "Member_4_leaveHome_reason ";
    private static final String KEY_MEMBER_4_EDUCATION = "Member_4_education ";
    private static final String KEY_MEMBER_4_SCHOOLTYPE = "Member_4_schoolType ";
    private static final String KEY_MEMBER_4_LEAVESCHOOL_REASON = "Member_4_leaveSchool_reason ";
    private static final String KEY_MEMBER_4_SCHOOLLEVEL = "Member_4_schoolLevel ";
    private static final String KEY_MEMBER_4_INCOMESOURCE = "Member_4_incomeSource ";
    private static final String KEY_MEMBER_4_ABROAD_COUNTRY = "Member_4_abroad_country ";
    private static final String KEY_MEMBER_4_ABROAD_MONEYTRANSFER = "Member_4_abroad_moneyTransfer ";
    private static final String KEY_MEMBER_4_SKILLS = "Member_4_skills ";
    private static final String KEY_MEMBER_4_HASBANKAC = "Member_4_hasBankAC ";
    private static final String KEY_MEMBER_4_HASNOBANKAC = "Member_4_hasNoBankAC ";
    private static final String KEY_MEMBER_4_HASATM = "Member_4_hasATM ";
    private static final String KEY_MEMBER_4_HASNOATM = "Member_4_hasNoATM ";
    private static final String KEY_MEMBER_4_USEONLINEBANKING = "Member_4_useOnlineBanking ";
    private static final String KEY_MEMBER_4_NOTUSEONLINEBANKING = "Member_4_notUseOnlineBanking ";
    private static final String KEY_MEMBER_4_REGULARDEPOSIT = "Member_4_regularDeposit ";
    private static final String KEY_MEMBER_4_NOTREGULARDEPOSIT = "Member_4_notRegularDeposit ";
    private static final String KEY_MEMBER_4_REGULARDEPOSIT_TO = "Member_4_regularDeposit_to ";
    private static final String KEY_MEMBER_4_ISHEALTHY = "Member_4_isHealthy ";
    private static final String KEY_MEMBER_4_ISDISABLED = "Member_4_isDisabled ";
    private static final String KEY_MEMBER_4_DISABILITYTYPE = "Member_4_disabilityType ";
    private static final String KEY_MEMBER_4_HASDISABILITYCARD = "Member_4_hasDisabilityCard ";
    private static final String KEY_MEMBER_4_HASNODISABILITYCARD = "Member_4_hasNoDisabilityCard ";
    private static final String KEY_MEMBER_4_HASDISEASE12MONTH = "Member_4_hasDisease12Month ";
    private static final String KEY_MEMBER_4_NODISEASE12MONTH = "Member_4_noDisease12Month ";
    private static final String KEY_MEMBER_4_HASLONGTERMDISEASE = "Member_4_hasLongTermDisease ";
    private static final String KEY_MEMBER_4_NOLONGTERMDISEASE = "Member_4_noLongTermDisease ";
    private static final String KEY_MEMBER_4_LONGTERMDISEASENAME = "Member_4_longTermDiseaseName ";
    private static final String KEY_MEMBER_4_HASCOMMUNICABLEDISEASE = "Member_4_hasCommunicableDisease ";
    private static final String KEY_MEMBER_4_NOCOMMUNICABLEDISEASE = "Member_4_noCommunicableDisease ";
    private static final String KEY_MEMBER_4_COMMUNICABLEDISEASENAME = "Member_4_communicableDiseaseName ";
    private static final String KEY_MEMBER_4_USESVACCINE = "Member_4_usesVaccine ";
    private static final String KEY_MEMBER_4_SOCIALIDENTITY = "Member_4_socialIdentity ";
    private static final String KEY_MEMBER_4_SOCIALSECURITY_TYPE = "Member_4_socialSecurity_type ";
    private static final String KEY_MEMBER_4_MARITALSTATUS = "Member_4_maritalStatus ";
    private static final String KEY_MEMBER_4_SOCIALINVOLVEMENTS = "Member_4_socialInvolvements ";
    private static final String KEY_MEMBER_4_HASRECEIVEDTRAINING = "Member_4_hasReceivedTraining ";
    private static final String KEY_MEMBER_4_NOTRECEIVEDTRAINING = "Member_4_notReceivedTraining ";
    private static final String KEY_MEMBER_4_TRAININGLIST = "Member_4_trainingList ";
    private static final String KEY_MEMBER_4_ISPOLITICALINFLUENCER = "Member_4_isPoliticalInfluencer ";
    private static final String KEY_MEMBER_4_ISNOTPOLITICALINFLUENCER = "Member_4_isNotPoliticalInfluencer ";
    private static final String KEY_MEMBER_4_POLITICALINFLUENCERLEVEL = "Member_4_politicalInfluencerLevel ";
    private static final String KEY_MEMBER_4_TRAVELWORK = "Member_4_travelWork ";
    private static final String KEY_MEMBER_4_TRAVELBUSINESS = "Member_4_travelBusiness ";
    private static final String KEY_MEMBER_4_TRAVELEDUCATION = "Member_4_travelEducation ";
    private static final String KEY_MEMBER_4_TRAVELOTHERS = "Member_4_travelOthers ";

    private static final String KEY_MEMBER_5_NAME = "Member_5_name ";
    private static final String KEY_MEMBER_5_CAST = "Member_5_cast ";
    private static final String KEY_MEMBER_5_GENDER = "Member_5_gender ";
    private static final String KEY_MEMBER_5_AGE = "Member_5_age ";
    private static final String KEY_MEMBER_5_RELATIONTOOWNER = "Member_5_relationToOwner ";
    private static final String KEY_MEMBER_5_BIRTHPLACE = "Member_5_birthplace ";
    private static final String KEY_MEMBER_5_HASEMAIL = "Member_5_hasEmail ";
    private static final String KEY_MEMBER_5_HASNOEMAIL = "Member_5_hasNoEmail ";
    private static final String KEY_MEMBER_5_HASLEFTHOME6MONTH = "Member_5_hasLeftHome6Month ";
    private static final String KEY_MEMBER_5_HASNOTLEFTHOME6MONTH = "Member_5_hasNotLeftHome6Month ";
    private static final String KEY_MEMBER_5_LEAVEHOME_PLACE = "Member_5_leaveHome_place ";
    private static final String KEY_MEMBER_5_LEAVEHOME_REASON = "Member_5_leaveHome_reason ";
    private static final String KEY_MEMBER_5_EDUCATION = "Member_5_education ";
    private static final String KEY_MEMBER_5_SCHOOLTYPE = "Member_5_schoolType ";
    private static final String KEY_MEMBER_5_LEAVESCHOOL_REASON = "Member_5_leaveSchool_reason ";
    private static final String KEY_MEMBER_5_SCHOOLLEVEL = "Member_5_schoolLevel ";
    private static final String KEY_MEMBER_5_INCOMESOURCE = "Member_5_incomeSource ";
    private static final String KEY_MEMBER_5_ABROAD_COUNTRY = "Member_5_abroad_country ";
    private static final String KEY_MEMBER_5_ABROAD_MONEYTRANSFER = "Member_5_abroad_moneyTransfer ";
    private static final String KEY_MEMBER_5_SKILLS = "Member_5_skills ";
    private static final String KEY_MEMBER_5_HASBANKAC = "Member_5_hasBankAC ";
    private static final String KEY_MEMBER_5_HASNOBANKAC = "Member_5_hasNoBankAC ";
    private static final String KEY_MEMBER_5_HASATM = "Member_5_hasATM ";
    private static final String KEY_MEMBER_5_HASNOATM = "Member_5_hasNoATM ";
    private static final String KEY_MEMBER_5_USEONLINEBANKING = "Member_5_useOnlineBanking ";
    private static final String KEY_MEMBER_5_NOTUSEONLINEBANKING = "Member_5_notUseOnlineBanking ";
    private static final String KEY_MEMBER_5_REGULARDEPOSIT = "Member_5_regularDeposit ";
    private static final String KEY_MEMBER_5_NOTREGULARDEPOSIT = "Member_5_notRegularDeposit ";
    private static final String KEY_MEMBER_5_REGULARDEPOSIT_TO = "Member_5_regularDeposit_to ";
    private static final String KEY_MEMBER_5_ISHEALTHY = "Member_5_isHealthy ";
    private static final String KEY_MEMBER_5_ISDISABLED = "Member_5_isDisabled ";
    private static final String KEY_MEMBER_5_DISABILITYTYPE = "Member_5_disabilityType ";
    private static final String KEY_MEMBER_5_HASDISABILITYCARD = "Member_5_hasDisabilityCard ";
    private static final String KEY_MEMBER_5_HASNODISABILITYCARD = "Member_5_hasNoDisabilityCard ";
    private static final String KEY_MEMBER_5_HASDISEASE12MONTH = "Member_5_hasDisease12Month ";
    private static final String KEY_MEMBER_5_NODISEASE12MONTH = "Member_5_noDisease12Month ";
    private static final String KEY_MEMBER_5_HASLONGTERMDISEASE = "Member_5_hasLongTermDisease ";
    private static final String KEY_MEMBER_5_NOLONGTERMDISEASE = "Member_5_noLongTermDisease ";
    private static final String KEY_MEMBER_5_LONGTERMDISEASENAME = "Member_5_longTermDiseaseName ";
    private static final String KEY_MEMBER_5_HASCOMMUNICABLEDISEASE = "Member_5_hasCommunicableDisease ";
    private static final String KEY_MEMBER_5_NOCOMMUNICABLEDISEASE = "Member_5_noCommunicableDisease ";
    private static final String KEY_MEMBER_5_COMMUNICABLEDISEASENAME = "Member_5_communicableDiseaseName ";
    private static final String KEY_MEMBER_5_USESVACCINE = "Member_5_usesVaccine ";
    private static final String KEY_MEMBER_5_SOCIALIDENTITY = "Member_5_socialIdentity ";
    private static final String KEY_MEMBER_5_SOCIALSECURITY_TYPE = "Member_5_socialSecurity_type ";
    private static final String KEY_MEMBER_5_MARITALSTATUS = "Member_5_maritalStatus ";
    private static final String KEY_MEMBER_5_SOCIALINVOLVEMENTS = "Member_5_socialInvolvements ";
    private static final String KEY_MEMBER_5_HASRECEIVEDTRAINING = "Member_5_hasReceivedTraining ";
    private static final String KEY_MEMBER_5_NOTRECEIVEDTRAINING = "Member_5_notReceivedTraining ";
    private static final String KEY_MEMBER_5_TRAININGLIST = "Member_5_trainingList ";
    private static final String KEY_MEMBER_5_ISPOLITICALINFLUENCER = "Member_5_isPoliticalInfluencer ";
    private static final String KEY_MEMBER_5_ISNOTPOLITICALINFLUENCER = "Member_5_isNotPoliticalInfluencer ";
    private static final String KEY_MEMBER_5_POLITICALINFLUENCERLEVEL = "Member_5_politicalInfluencerLevel ";
    private static final String KEY_MEMBER_5_TRAVELWORK = "Member_5_travelWork ";
    private static final String KEY_MEMBER_5_TRAVELBUSINESS = "Member_5_travelBusiness ";
    private static final String KEY_MEMBER_5_TRAVELEDUCATION = "Member_5_travelEducation ";
    private static final String KEY_MEMBER_5_TRAVELOTHERS = "Member_5_travelOthers ";

    private static final String KEY_MEMBER_6_NAME = "Member_6_name ";
    private static final String KEY_MEMBER_6_CAST = "Member_6_cast ";
    private static final String KEY_MEMBER_6_GENDER = "Member_6_gender ";
    private static final String KEY_MEMBER_6_AGE = "Member_6_age ";
    private static final String KEY_MEMBER_6_RELATIONTOOWNER = "Member_6_relationToOwner ";
    private static final String KEY_MEMBER_6_BIRTHPLACE = "Member_6_birthplace ";
    private static final String KEY_MEMBER_6_HASEMAIL = "Member_6_hasEmail ";
    private static final String KEY_MEMBER_6_HASNOEMAIL = "Member_6_hasNoEmail ";
    private static final String KEY_MEMBER_6_HASLEFTHOME6MONTH = "Member_6_hasLeftHome6Month ";
    private static final String KEY_MEMBER_6_HASNOTLEFTHOME6MONTH = "Member_6_hasNotLeftHome6Month ";
    private static final String KEY_MEMBER_6_LEAVEHOME_PLACE = "Member_6_leaveHome_place ";
    private static final String KEY_MEMBER_6_LEAVEHOME_REASON = "Member_6_leaveHome_reason ";
    private static final String KEY_MEMBER_6_EDUCATION = "Member_6_education ";
    private static final String KEY_MEMBER_6_SCHOOLTYPE = "Member_6_schoolType ";
    private static final String KEY_MEMBER_6_LEAVESCHOOL_REASON = "Member_6_leaveSchool_reason ";
    private static final String KEY_MEMBER_6_SCHOOLLEVEL = "Member_6_schoolLevel ";
    private static final String KEY_MEMBER_6_INCOMESOURCE = "Member_6_incomeSource ";
    private static final String KEY_MEMBER_6_ABROAD_COUNTRY = "Member_6_abroad_country ";
    private static final String KEY_MEMBER_6_ABROAD_MONEYTRANSFER = "Member_6_abroad_moneyTransfer ";
    private static final String KEY_MEMBER_6_SKILLS = "Member_6_skills ";
    private static final String KEY_MEMBER_6_HASBANKAC = "Member_6_hasBankAC ";
    private static final String KEY_MEMBER_6_HASNOBANKAC = "Member_6_hasNoBankAC ";
    private static final String KEY_MEMBER_6_HASATM = "Member_6_hasATM ";
    private static final String KEY_MEMBER_6_HASNOATM = "Member_6_hasNoATM ";
    private static final String KEY_MEMBER_6_USEONLINEBANKING = "Member_6_useOnlineBanking ";
    private static final String KEY_MEMBER_6_NOTUSEONLINEBANKING = "Member_6_notUseOnlineBanking ";
    private static final String KEY_MEMBER_6_REGULARDEPOSIT = "Member_6_regularDeposit ";
    private static final String KEY_MEMBER_6_NOTREGULARDEPOSIT = "Member_6_notRegularDeposit ";
    private static final String KEY_MEMBER_6_REGULARDEPOSIT_TO = "Member_6_regularDeposit_to ";
    private static final String KEY_MEMBER_6_ISHEALTHY = "Member_6_isHealthy ";
    private static final String KEY_MEMBER_6_ISDISABLED = "Member_6_isDisabled ";
    private static final String KEY_MEMBER_6_DISABILITYTYPE = "Member_6_disabilityType ";
    private static final String KEY_MEMBER_6_HASDISABILITYCARD = "Member_6_hasDisabilityCard ";
    private static final String KEY_MEMBER_6_HASNODISABILITYCARD = "Member_6_hasNoDisabilityCard ";
    private static final String KEY_MEMBER_6_HASDISEASE12MONTH = "Member_6_hasDisease12Month ";
    private static final String KEY_MEMBER_6_NODISEASE12MONTH = "Member_6_noDisease12Month ";
    private static final String KEY_MEMBER_6_HASLONGTERMDISEASE = "Member_6_hasLongTermDisease ";
    private static final String KEY_MEMBER_6_NOLONGTERMDISEASE = "Member_6_noLongTermDisease ";
    private static final String KEY_MEMBER_6_LONGTERMDISEASENAME = "Member_6_longTermDiseaseName ";
    private static final String KEY_MEMBER_6_HASCOMMUNICABLEDISEASE = "Member_6_hasCommunicableDisease ";
    private static final String KEY_MEMBER_6_NOCOMMUNICABLEDISEASE = "Member_6_noCommunicableDisease ";
    private static final String KEY_MEMBER_6_COMMUNICABLEDISEASENAME = "Member_6_communicableDiseaseName ";
    private static final String KEY_MEMBER_6_USESVACCINE = "Member_6_usesVaccine ";
    private static final String KEY_MEMBER_6_SOCIALIDENTITY = "Member_6_socialIdentity ";
    private static final String KEY_MEMBER_6_SOCIALSECURITY_TYPE = "Member_6_socialSecurity_type ";
    private static final String KEY_MEMBER_6_MARITALSTATUS = "Member_6_maritalStatus ";
    private static final String KEY_MEMBER_6_SOCIALINVOLVEMENTS = "Member_6_socialInvolvements ";
    private static final String KEY_MEMBER_6_HASRECEIVEDTRAINING = "Member_6_hasReceivedTraining ";
    private static final String KEY_MEMBER_6_NOTRECEIVEDTRAINING = "Member_6_notReceivedTraining ";
    private static final String KEY_MEMBER_6_TRAININGLIST = "Member_6_trainingList ";
    private static final String KEY_MEMBER_6_ISPOLITICALINFLUENCER = "Member_6_isPoliticalInfluencer ";
    private static final String KEY_MEMBER_6_ISNOTPOLITICALINFLUENCER = "Member_6_isNotPoliticalInfluencer ";
    private static final String KEY_MEMBER_6_POLITICALINFLUENCERLEVEL = "Member_6_politicalInfluencerLevel ";
    private static final String KEY_MEMBER_6_TRAVELWORK = "Member_6_travelWork ";
    private static final String KEY_MEMBER_6_TRAVELBUSINESS = "Member_6_travelBusiness ";
    private static final String KEY_MEMBER_6_TRAVELEDUCATION = "Member_6_travelEducation ";
    private static final String KEY_MEMBER_6_TRAVELOTHERS = "Member_6_travelOthers ";

    private static final String KEY_MEMBER_7_NAME = "Member_7_name ";
    private static final String KEY_MEMBER_7_CAST = "Member_7_cast ";
    private static final String KEY_MEMBER_7_GENDER = "Member_7_gender ";
    private static final String KEY_MEMBER_7_AGE = "Member_7_age ";
    private static final String KEY_MEMBER_7_RELATIONTOOWNER = "Member_7_relationToOwner ";
    private static final String KEY_MEMBER_7_BIRTHPLACE = "Member_7_birthplace ";
    private static final String KEY_MEMBER_7_HASEMAIL = "Member_7_hasEmail ";
    private static final String KEY_MEMBER_7_HASNOEMAIL = "Member_7_hasNoEmail ";
    private static final String KEY_MEMBER_7_HASLEFTHOME6MONTH = "Member_7_hasLeftHome6Month ";
    private static final String KEY_MEMBER_7_HASNOTLEFTHOME6MONTH = "Member_7_hasNotLeftHome6Month ";
    private static final String KEY_MEMBER_7_LEAVEHOME_PLACE = "Member_7_leaveHome_place ";
    private static final String KEY_MEMBER_7_LEAVEHOME_REASON = "Member_7_leaveHome_reason ";
    private static final String KEY_MEMBER_7_EDUCATION = "Member_7_education ";
    private static final String KEY_MEMBER_7_SCHOOLTYPE = "Member_7_schoolType ";
    private static final String KEY_MEMBER_7_LEAVESCHOOL_REASON = "Member_7_leaveSchool_reason ";
    private static final String KEY_MEMBER_7_SCHOOLLEVEL = "Member_7_schoolLevel ";
    private static final String KEY_MEMBER_7_INCOMESOURCE = "Member_7_incomeSource ";
    private static final String KEY_MEMBER_7_ABROAD_COUNTRY = "Member_7_abroad_country ";
    private static final String KEY_MEMBER_7_ABROAD_MONEYTRANSFER = "Member_7_abroad_moneyTransfer ";
    private static final String KEY_MEMBER_7_SKILLS = "Member_7_skills ";
    private static final String KEY_MEMBER_7_HASBANKAC = "Member_7_hasBankAC ";
    private static final String KEY_MEMBER_7_HASNOBANKAC = "Member_7_hasNoBankAC ";
    private static final String KEY_MEMBER_7_HASATM = "Member_7_hasATM ";
    private static final String KEY_MEMBER_7_HASNOATM = "Member_7_hasNoATM ";
    private static final String KEY_MEMBER_7_USEONLINEBANKING = "Member_7_useOnlineBanking ";
    private static final String KEY_MEMBER_7_NOTUSEONLINEBANKING = "Member_7_notUseOnlineBanking ";
    private static final String KEY_MEMBER_7_REGULARDEPOSIT = "Member_7_regularDeposit ";
    private static final String KEY_MEMBER_7_NOTREGULARDEPOSIT = "Member_7_notRegularDeposit ";
    private static final String KEY_MEMBER_7_REGULARDEPOSIT_TO = "Member_7_regularDeposit_to ";
    private static final String KEY_MEMBER_7_ISHEALTHY = "Member_7_isHealthy ";
    private static final String KEY_MEMBER_7_ISDISABLED = "Member_7_isDisabled ";
    private static final String KEY_MEMBER_7_DISABILITYTYPE = "Member_7_disabilityType ";
    private static final String KEY_MEMBER_7_HASDISABILITYCARD = "Member_7_hasDisabilityCard ";
    private static final String KEY_MEMBER_7_HASNODISABILITYCARD = "Member_7_hasNoDisabilityCard ";
    private static final String KEY_MEMBER_7_HASDISEASE12MONTH = "Member_7_hasDisease12Month ";
    private static final String KEY_MEMBER_7_NODISEASE12MONTH = "Member_7_noDisease12Month ";
    private static final String KEY_MEMBER_7_HASLONGTERMDISEASE = "Member_7_hasLongTermDisease ";
    private static final String KEY_MEMBER_7_NOLONGTERMDISEASE = "Member_7_noLongTermDisease ";
    private static final String KEY_MEMBER_7_LONGTERMDISEASENAME = "Member_7_longTermDiseaseName ";
    private static final String KEY_MEMBER_7_HASCOMMUNICABLEDISEASE = "Member_7_hasCommunicableDisease ";
    private static final String KEY_MEMBER_7_NOCOMMUNICABLEDISEASE = "Member_7_noCommunicableDisease ";
    private static final String KEY_MEMBER_7_COMMUNICABLEDISEASENAME = "Member_7_communicableDiseaseName ";
    private static final String KEY_MEMBER_7_USESVACCINE = "Member_7_usesVaccine ";
    private static final String KEY_MEMBER_7_SOCIALIDENTITY = "Member_7_socialIdentity ";
    private static final String KEY_MEMBER_7_SOCIALSECURITY_TYPE = "Member_7_socialSecurity_type ";
    private static final String KEY_MEMBER_7_MARITALSTATUS = "Member_7_maritalStatus ";
    private static final String KEY_MEMBER_7_SOCIALINVOLVEMENTS = "Member_7_socialInvolvements ";
    private static final String KEY_MEMBER_7_HASRECEIVEDTRAINING = "Member_7_hasReceivedTraining ";
    private static final String KEY_MEMBER_7_NOTRECEIVEDTRAINING = "Member_7_notReceivedTraining ";
    private static final String KEY_MEMBER_7_TRAININGLIST = "Member_7_trainingList ";
    private static final String KEY_MEMBER_7_ISPOLITICALINFLUENCER = "Member_7_isPoliticalInfluencer ";
    private static final String KEY_MEMBER_7_ISNOTPOLITICALINFLUENCER = "Member_7_isNotPoliticalInfluencer ";
    private static final String KEY_MEMBER_7_POLITICALINFLUENCERLEVEL = "Member_7_politicalInfluencerLevel ";
    private static final String KEY_MEMBER_7_TRAVELWORK = "Member_7_travelWork ";
    private static final String KEY_MEMBER_7_TRAVELBUSINESS = "Member_7_travelBusiness ";
    private static final String KEY_MEMBER_7_TRAVELEDUCATION = "Member_7_travelEducation ";
    private static final String KEY_MEMBER_7_TRAVELOTHERS = "Member_7_travelOthers ";

    private static final String KEY_MEMBER_8_NAME = "Member_8_name ";
    private static final String KEY_MEMBER_8_CAST = "Member_8_cast ";
    private static final String KEY_MEMBER_8_GENDER = "Member_8_gender ";
    private static final String KEY_MEMBER_8_AGE = "Member_8_age ";
    private static final String KEY_MEMBER_8_RELATIONTOOWNER = "Member_8_relationToOwner ";
    private static final String KEY_MEMBER_8_BIRTHPLACE = "Member_8_birthplace ";
    private static final String KEY_MEMBER_8_HASEMAIL = "Member_8_hasEmail ";
    private static final String KEY_MEMBER_8_HASNOEMAIL = "Member_8_hasNoEmail ";
    private static final String KEY_MEMBER_8_HASLEFTHOME6MONTH = "Member_8_hasLeftHome6Month ";
    private static final String KEY_MEMBER_8_HASNOTLEFTHOME6MONTH = "Member_8_hasNotLeftHome6Month ";
    private static final String KEY_MEMBER_8_LEAVEHOME_PLACE = "Member_8_leaveHome_place ";
    private static final String KEY_MEMBER_8_LEAVEHOME_REASON = "Member_8_leaveHome_reason ";
    private static final String KEY_MEMBER_8_EDUCATION = "Member_8_education ";
    private static final String KEY_MEMBER_8_SCHOOLTYPE = "Member_8_schoolType ";
    private static final String KEY_MEMBER_8_LEAVESCHOOL_REASON = "Member_8_leaveSchool_reason ";
    private static final String KEY_MEMBER_8_SCHOOLLEVEL = "Member_8_schoolLevel ";
    private static final String KEY_MEMBER_8_INCOMESOURCE = "Member_8_incomeSource ";
    private static final String KEY_MEMBER_8_ABROAD_COUNTRY = "Member_8_abroad_country ";
    private static final String KEY_MEMBER_8_ABROAD_MONEYTRANSFER = "Member_8_abroad_moneyTransfer ";
    private static final String KEY_MEMBER_8_SKILLS = "Member_8_skills ";
    private static final String KEY_MEMBER_8_HASBANKAC = "Member_8_hasBankAC ";
    private static final String KEY_MEMBER_8_HASNOBANKAC = "Member_8_hasNoBankAC ";
    private static final String KEY_MEMBER_8_HASATM = "Member_8_hasATM ";
    private static final String KEY_MEMBER_8_HASNOATM = "Member_8_hasNoATM ";
    private static final String KEY_MEMBER_8_USEONLINEBANKING = "Member_8_useOnlineBanking ";
    private static final String KEY_MEMBER_8_NOTUSEONLINEBANKING = "Member_8_notUseOnlineBanking ";
    private static final String KEY_MEMBER_8_REGULARDEPOSIT = "Member_8_regularDeposit ";
    private static final String KEY_MEMBER_8_NOTREGULARDEPOSIT = "Member_8_notRegularDeposit ";
    private static final String KEY_MEMBER_8_REGULARDEPOSIT_TO = "Member_8_regularDeposit_to ";
    private static final String KEY_MEMBER_8_ISHEALTHY = "Member_8_isHealthy ";
    private static final String KEY_MEMBER_8_ISDISABLED = "Member_8_isDisabled ";
    private static final String KEY_MEMBER_8_DISABILITYTYPE = "Member_8_disabilityType ";
    private static final String KEY_MEMBER_8_HASDISABILITYCARD = "Member_8_hasDisabilityCard ";
    private static final String KEY_MEMBER_8_HASNODISABILITYCARD = "Member_8_hasNoDisabilityCard ";
    private static final String KEY_MEMBER_8_HASDISEASE12MONTH = "Member_8_hasDisease12Month ";
    private static final String KEY_MEMBER_8_NODISEASE12MONTH = "Member_8_noDisease12Month ";
    private static final String KEY_MEMBER_8_HASLONGTERMDISEASE = "Member_8_hasLongTermDisease ";
    private static final String KEY_MEMBER_8_NOLONGTERMDISEASE = "Member_8_noLongTermDisease ";
    private static final String KEY_MEMBER_8_LONGTERMDISEASENAME = "Member_8_longTermDiseaseName ";
    private static final String KEY_MEMBER_8_HASCOMMUNICABLEDISEASE = "Member_8_hasCommunicableDisease ";
    private static final String KEY_MEMBER_8_NOCOMMUNICABLEDISEASE = "Member_8_noCommunicableDisease ";
    private static final String KEY_MEMBER_8_COMMUNICABLEDISEASENAME = "Member_8_communicableDiseaseName ";
    private static final String KEY_MEMBER_8_USESVACCINE = "Member_8_usesVaccine ";
    private static final String KEY_MEMBER_8_SOCIALIDENTITY = "Member_8_socialIdentity ";
    private static final String KEY_MEMBER_8_SOCIALSECURITY_TYPE = "Member_8_socialSecurity_type ";
    private static final String KEY_MEMBER_8_MARITALSTATUS = "Member_8_maritalStatus ";
    private static final String KEY_MEMBER_8_SOCIALINVOLVEMENTS = "Member_8_socialInvolvements ";
    private static final String KEY_MEMBER_8_HASRECEIVEDTRAINING = "Member_8_hasReceivedTraining ";
    private static final String KEY_MEMBER_8_NOTRECEIVEDTRAINING = "Member_8_notReceivedTraining ";
    private static final String KEY_MEMBER_8_TRAININGLIST = "Member_8_trainingList ";
    private static final String KEY_MEMBER_8_ISPOLITICALINFLUENCER = "Member_8_isPoliticalInfluencer ";
    private static final String KEY_MEMBER_8_ISNOTPOLITICALINFLUENCER = "Member_8_isNotPoliticalInfluencer ";
    private static final String KEY_MEMBER_8_POLITICALINFLUENCERLEVEL = "Member_8_politicalInfluencerLevel ";
    private static final String KEY_MEMBER_8_TRAVELWORK = "Member_8_travelWork ";
    private static final String KEY_MEMBER_8_TRAVELBUSINESS = "Member_8_travelBusiness ";
    private static final String KEY_MEMBER_8_TRAVELEDUCATION = "Member_8_travelEducation ";
    private static final String KEY_MEMBER_8_TRAVELOTHERS = "Member_8_travelOthers ";

    private static final String KEY_MEMBER_9_NAME = "Member_9_name ";
    private static final String KEY_MEMBER_9_CAST = "Member_9_cast ";
    private static final String KEY_MEMBER_9_GENDER = "Member_9_gender ";
    private static final String KEY_MEMBER_9_AGE = "Member_9_age ";
    private static final String KEY_MEMBER_9_RELATIONTOOWNER = "Member_9_relationToOwner ";
    private static final String KEY_MEMBER_9_BIRTHPLACE = "Member_9_birthplace ";
    private static final String KEY_MEMBER_9_HASEMAIL = "Member_9_hasEmail ";
    private static final String KEY_MEMBER_9_HASNOEMAIL = "Member_9_hasNoEmail ";
    private static final String KEY_MEMBER_9_HASLEFTHOME6MONTH = "Member_9_hasLeftHome6Month ";
    private static final String KEY_MEMBER_9_HASNOTLEFTHOME6MONTH = "Member_9_hasNotLeftHome6Month ";
    private static final String KEY_MEMBER_9_LEAVEHOME_PLACE = "Member_9_leaveHome_place ";
    private static final String KEY_MEMBER_9_LEAVEHOME_REASON = "Member_9_leaveHome_reason ";
    private static final String KEY_MEMBER_9_EDUCATION = "Member_9_education ";
    private static final String KEY_MEMBER_9_SCHOOLTYPE = "Member_9_schoolType ";
    private static final String KEY_MEMBER_9_LEAVESCHOOL_REASON = "Member_9_leaveSchool_reason ";
    private static final String KEY_MEMBER_9_SCHOOLLEVEL = "Member_9_schoolLevel ";
    private static final String KEY_MEMBER_9_INCOMESOURCE = "Member_9_incomeSource ";
    private static final String KEY_MEMBER_9_ABROAD_COUNTRY = "Member_9_abroad_country ";
    private static final String KEY_MEMBER_9_ABROAD_MONEYTRANSFER = "Member_9_abroad_moneyTransfer ";
    private static final String KEY_MEMBER_9_SKILLS = "Member_9_skills ";
    private static final String KEY_MEMBER_9_HASBANKAC = "Member_9_hasBankAC ";
    private static final String KEY_MEMBER_9_HASNOBANKAC = "Member_9_hasNoBankAC ";
    private static final String KEY_MEMBER_9_HASATM = "Member_9_hasATM ";
    private static final String KEY_MEMBER_9_HASNOATM = "Member_9_hasNoATM ";
    private static final String KEY_MEMBER_9_USEONLINEBANKING = "Member_9_useOnlineBanking ";
    private static final String KEY_MEMBER_9_NOTUSEONLINEBANKING = "Member_9_notUseOnlineBanking ";
    private static final String KEY_MEMBER_9_REGULARDEPOSIT = "Member_9_regularDeposit ";
    private static final String KEY_MEMBER_9_NOTREGULARDEPOSIT = "Member_9_notRegularDeposit ";
    private static final String KEY_MEMBER_9_REGULARDEPOSIT_TO = "Member_9_regularDeposit_to ";
    private static final String KEY_MEMBER_9_ISHEALTHY = "Member_9_isHealthy ";
    private static final String KEY_MEMBER_9_ISDISABLED = "Member_9_isDisabled ";
    private static final String KEY_MEMBER_9_DISABILITYTYPE = "Member_9_disabilityType ";
    private static final String KEY_MEMBER_9_HASDISABILITYCARD = "Member_9_hasDisabilityCard ";
    private static final String KEY_MEMBER_9_HASNODISABILITYCARD = "Member_9_hasNoDisabilityCard ";
    private static final String KEY_MEMBER_9_HASDISEASE12MONTH = "Member_9_hasDisease12Month ";
    private static final String KEY_MEMBER_9_NODISEASE12MONTH = "Member_9_noDisease12Month ";
    private static final String KEY_MEMBER_9_HASLONGTERMDISEASE = "Member_9_hasLongTermDisease ";
    private static final String KEY_MEMBER_9_NOLONGTERMDISEASE = "Member_9_noLongTermDisease ";
    private static final String KEY_MEMBER_9_LONGTERMDISEASENAME = "Member_9_longTermDiseaseName ";
    private static final String KEY_MEMBER_9_HASCOMMUNICABLEDISEASE = "Member_9_hasCommunicableDisease ";
    private static final String KEY_MEMBER_9_NOCOMMUNICABLEDISEASE = "Member_9_noCommunicableDisease ";
    private static final String KEY_MEMBER_9_COMMUNICABLEDISEASENAME = "Member_9_communicableDiseaseName ";
    private static final String KEY_MEMBER_9_USESVACCINE = "Member_9_usesVaccine ";
    private static final String KEY_MEMBER_9_SOCIALIDENTITY = "Member_9_socialIdentity ";
    private static final String KEY_MEMBER_9_SOCIALSECURITY_TYPE = "Member_9_socialSecurity_type ";
    private static final String KEY_MEMBER_9_MARITALSTATUS = "Member_9_maritalStatus ";
    private static final String KEY_MEMBER_9_SOCIALINVOLVEMENTS = "Member_9_socialInvolvements ";
    private static final String KEY_MEMBER_9_HASRECEIVEDTRAINING = "Member_9_hasReceivedTraining ";
    private static final String KEY_MEMBER_9_NOTRECEIVEDTRAINING = "Member_9_notReceivedTraining ";
    private static final String KEY_MEMBER_9_TRAININGLIST = "Member_9_trainingList ";
    private static final String KEY_MEMBER_9_ISPOLITICALINFLUENCER = "Member_9_isPoliticalInfluencer ";
    private static final String KEY_MEMBER_9_ISNOTPOLITICALINFLUENCER = "Member_9_isNotPoliticalInfluencer ";
    private static final String KEY_MEMBER_9_POLITICALINFLUENCERLEVEL = "Member_9_politicalInfluencerLevel ";
    private static final String KEY_MEMBER_9_TRAVELWORK = "Member_9_travelWork ";
    private static final String KEY_MEMBER_9_TRAVELBUSINESS = "Member_9_travelBusiness ";
    private static final String KEY_MEMBER_9_TRAVELEDUCATION = "Member_9_travelEducation ";
    private static final String KEY_MEMBER_9_TRAVELOTHERS = "Member_9_travelOthers ";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("

                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_LAT + " TEXT,"
                + KEY_LNG + " TEXT,"
                + KEY_ALT + " TEXT,"
                + KEY_PRADESH + " TEXT,"
                + KEY_JILLA + " TEXT,"
                + KEY_NAGARPALIKA + " TEXT,"
                + KEY_WARD + " TEXT,"
                + KEY_BASTI + " TEXT,"
                + KEY_TOLENAME + " TEXT,"
                + KEY_SADAKNAME + " TEXT,"

                + KEY_JATI + " TEXT,"
                + KEY_VASA + " TEXT,"
                + KEY_DHARMA0 + " TEXT,"
                + KEY_GHARDHANINAME + " TEXT,"
                + KEY_GHARDHANISEX + " TEXT,"
                + KEY_GHARDHANIPHONE + " TEXT,"

                + KEY_TENANT_FAMILY_COUNT + " TEXT,"
                + KEY_INFORMER_NAME + " TEXT,"
                + KEY_INFORMER_GENDER + " TEXT,"
                + KEY_INFORMER_AGE + " TEXT,"
                + KEY_INFORMER_EMAIL + " TEXT,"
                + KEY_SWAMITTO + " TEXT,"
                + KEY_BASAI_ABADHI + " TEXT,"
                + KEY_JATIYA_SAMUHA + " TEXT,"
                + KEY_JATJATI + " TEXT,"
                + KEY_DHARMA + " TEXT,"
                + KEY_MATRIBHASHA + " TEXT,"
                + KEY_THAR + " TEXT,"
                + KEY_PARIWAR_BASOBAS_AVASTHA + " TEXT,"
                + KEY_BASAI_SARNU_KARAN + " TEXT,"
                + KEY_BASAI_AVADHI + " TEXT,"
                + KEY_NAGARPALIKA_MA_GHAR_BHAYEKO + " TEXT,"
                + KEY_GHAR_JAGGA_SWAMITTO_KISIM + " TEXT,"
                + KEY_NAGARPALIKA_MA_ANYA_GHAR_BHAYEKO + " TEXT,"
                + KEY_ANYA_GHAR_K_KO_LAGI + " TEXT,"
                + KEY_KITCHEN_ALAGGAI_BHAYEKO + " TEXT,"
                + KEY_KITCHEN_NIRMAN_AVASTHA + " TEXT,"
                + KEY_KITCHEN_NIRMAN_BARSA + " TEXT,"
                + KEY_BATTI_MAIN_SOURCE + " TEXT,"
                + KEY_ELECTRICITY_JADAN_BHAYEKO + " TEXT,"
                + KEY_ELECTRICITY_JADAN_KINA_NABHAYEKO + " TEXT,"
                + KEY_CULO_KO_PRAKAR + " TEXT,"
                + KEY_PAKAUNE_FUEL + " TEXT,"
                + KEY_DAAURA_KO_SOURCE + " TEXT,"
                + KEY_GAS_CYLINDER_TIKNE_DIN + " TEXT,"
                + KEY_OLD_CHULO_KO_LAGI_DAAURA_KG + " TEXT,"
                + KEY_MODERN_CHULO_KO_LAGI_DAAURA_KG + " TEXT,"
                + KEY_MATTITEL_PERMONTH_LITRE + " TEXT,"
                + KEY_INDUCTION_HEATER_HOURSPERDAY + " TEXT,"
                + KEY_OVEN_HOURSPERDAY + " TEXT,"
                + KEY_PAANI_TATAUNE_UPAKARAN + " TEXT,"
                + KEY_PAANI_TATAUNA_CYLINDERPERYEAR + " TEXT,"
                + KEY_PAANI_TATAUNA_WOOD_KGPERMONTH_OLD_CHULO + " TEXT,"
                + KEY_PAANI_TATAUNA_WOOD_KGPERMONTH_MODERN + " TEXT,"
                + KEY_PAANI_TATAUNA_MATTITEL_PERMONTH + " TEXT,"
                + KEY_PAANI_TATAUNA_INDUCTION_HOURSPERDAY + " TEXT,"
                + KEY_PAANI_TATAUNA_OVEN_HOURSPERDAY + " TEXT,"
                + KEY_ROOM_HEATING_UPAKARAN + " TEXT,"
                + KEY_ROOM_COOLING_UPAKARAN + " TEXT,"
                + KEY_FAN_COUNT + " TEXT,"
                + KEY_FAN_WATT + " TEXT,"
                + KEY_FAN_HOURSPERDAY + " TEXT,"
                + KEY_AIRCONDITION_COUNT + " TEXT,"
                + KEY_AIRCONDITION_WATT + " TEXT,"
                + KEY_AIRCONDITION_HOURSPERDAY + " TEXT,"
                + KEY_BIJULI_LOAD_AMPERE + " TEXT,"
                + KEY_BIJULI_UNIT + " TEXT,"
                + KEY_WASHINGMACHINE_COUNT + " TEXT,"
                + KEY_FRIDGE_COUNT + " TEXT,"
                + KEY_SOLAR_COUNT + " TEXT,"
                + KEY_VACUUM_COUNT + " TEXT,"
                + KEY_INVERTER_COUNT + " TEXT,"
                + KEY_GENERATOR_COUNT + " TEXT,"
                + KEY_DISHWASHER_COUNT + " TEXT,"
                + KEY_CABLE_COUNT + " TEXT,"
                + KEY_OTHER_HOUSE_GADGET_COUNT + " TEXT,"
                + KEY_RADIO_COUNT + " TEXT,"
                + KEY_TELEVISION_COUNT + " TEXT,"
                + KEY_TELEPHONE_COUNT + " TEXT,"
                + KEY_CELLPHONE_COUNT + " TEXT,"
                + KEY_SMARTPHONE_COUNT + " TEXT,"
                + KEY_COMPUTER_LAPTOP_COUNT + " TEXT,"
                + KEY_INTERNET_COUNT + " TEXT,"
                + KEY_OTHER_COMMUNICATION_COUNT + " TEXT,"
                + KEY_INTERNET_SPEED_MBPS + " TEXT,"
                + KEY_CYCLE_COUNT + " TEXT,"
                + KEY_BIKE_COUNT + " TEXT,"
                + KEY_LIGHTWEIGHT_VEHICLE_COUNT + " TEXT,"
                + KEY_HEAVYWEIGHT_VEHICLE_COUNT + " TEXT,"
                + KEY_PUBLIC_TRANSPORT_VEHICLE_COUNT + " TEXT,"
                + KEY_FAMILY_MA_YATAYAT_LICENSE_BHAYEKO + " TEXT,"
                + KEY_LICENSE_BHAYEKO_COUNT + " TEXT,"
                + KEY_LICENSE_COUNT_2_WHEELER + " TEXT,"
                + KEY_LICENSE_COUNT_4_WHEELER + " TEXT,"
                + KEY_ONE_YEAR_MA_BIRAMI + " TEXT,"
                + KEY_BIRAMI_HUDA_KATA_LAGEKO + " TEXT,"
                + KEY_HEALTHPOST_JANA_HIDERA_TIME + " TEXT,"
                + KEY_HEALTHPOST_JANA_GAADI_TIME + " TEXT,"
                + KEY_TWO_YEAR_LE_VACCINE_LAGAYEKO + " TEXT,"
                + KEY_VACCINE_NAGAREKO_KARAN + " TEXT,"
                + KEY_TWO_YEAR_MA_PREGNANT + " TEXT,"
                + KEY_PREGNANT_KO_HEALTH_CHECKUP + " TEXT,"
                + KEY_PREGNANT_KO_CHECK_NAGARNE_KARAN + " TEXT,"
                + KEY_PREGNANT_KO_CHECK_GAREKO_COUNT + " TEXT,"
                + KEY_PREGNANT_LE_ICON_PILL_LINEGAREKO + " TEXT,"
                + KEY_PREGNANT_LE_JUKA_MEDICINE_LINEGAREKO + " TEXT,"
                + KEY_PREGNANT_LE_VITAMIN_LINEGAREKO + " TEXT,"
                + KEY_BACCHA_JANMAAUNE_STHAN + " TEXT,"
                + KEY_BACCHA_JANMAAUNA_SAHAYOG_GARNE + " TEXT,"
                + KEY_BACCHA_JANMAAUNA_HELP_NALINUKARAN + " TEXT,"
                + KEY_LAST12MONTHMA_UNDER_5YR_KO_DEATH + " TEXT,"
                + KEY_UNDER_5YR_DEATH_GENDER + " TEXT,"
                + KEY_UNDER_5YR_DEATH_AGE + " TEXT,"
                + KEY_UNDER_5YR_DEATH_KARAN + " TEXT,"
                + KEY_UNDER_5YR_KO_DEVELOPMENT_MEASURE_HUNCHA + " TEXT,"
                + KEY_AGE6MONTH_TO_6YR_VITAMIN_A + " TEXT,"
                + KEY_AGE1YR_TO_6YR_JUKA_MEDICINE + " TEXT,"
                + KEY_WINTER_MA_WARM_CLOTH + " TEXT,"
                + KEY_JHUUL_KO_BABYASTHA + " TEXT,"
                + KEY_REGULAR_HEALTH_CHECKUP_PERYEAR + " TEXT,"
                + KEY_PAST12MONTH_MA_DEATH_BHAYEKO + " TEXT,"
                + KEY_YOG_RA_ADHYATMIK_KENDRA_MA_JANEGAREKO + " TEXT,"
                + KEY_KHANEPAANI_SOURCE + " TEXT,"
                + KEY_PAANI_METER_JADAN_GAREYEKO + " TEXT,"
                + KEY_PAANI_SUFFICIENT_HUNE + " TEXT,"
                + KEY_PAANI_LINA_JANE_AAUNE_TIME + " TEXT,"
                + KEY_KHANEPAANI_QUALITY + " TEXT,"
                + KEY_PANI_PURIFY_GARNE_GAREKO + " TEXT,"
                + KEY_RAIN_WATER_COLLECT_GAREKO + " TEXT,"
                + KEY_RAINWATER_KO_USE + " TEXT,"
                + KEY_GHAAR_MA_DHAAL_ATTACH_BHAYEKO + " TEXT,"
                + KEY_DHAAL_ATTACH_BHAYEKO_TYPE + " TEXT,"
                + KEY_WASTE_WATER_MANAGEMENT + " TEXT,"
                + KEY_DECOMPOSE_NODECOMPOSE_SEPARATE_GARNE + " TEXT,"
                + KEY_SOLID_WASTE_MANAGEMENT + " TEXT,"

                + KEY_HASTOILET + " TEXT,"
                + KEY_HASNOTOILET + " TEXT,"
                + KEY_TOILETTYPE + " TEXT,"
                + KEY_TOILETWASTEMGMT + " TEXT,"
                + KEY_ONLINESERVICES + " TEXT,"
                + KEY_PRIMARYINCOMESOURCE + " TEXT,"
                + KEY_OWNSLAND + " TEXT,"
                + KEY_OWNSNOLAND + " TEXT,"
                + KEY_LANDLOCATION + " TEXT,"
                + KEY_LANDOWNEDBY + " TEXT,"
                + KEY_LANDUSEDAS + " TEXT,"
                + KEY_LANDONLEASE + " TEXT,"
                + KEY_LANDONNOLEASE + " TEXT,"
                + KEY_LANDONLEASEUSEDAS + " TEXT,"
                + KEY_PRIMARYAGROPRODUCTIONS + " TEXT,"
                + KEY_LASTYEARAGROPRODUCTIONS + " TEXT,"
                + KEY_ASSETANIMALS + " TEXT,"
                + KEY_ASSETBIRDS + " TEXT,"
                + KEY_ASSETSFISHES + " TEXT,"
                + KEY_ASSETSBEES + " TEXT,"
                + KEY_HORTICULTUREPRODUCTIONCOUNTS + " TEXT,"
                + KEY_SELLSPRODUCTIONS + " TEXT,"
                + KEY_NOTSELLSPRODUCTIONS + " TEXT,"
                + KEY_SELLPRODUCTIONLIST + " TEXT,"
                + KEY_SELLPRODUCTIONTO + " TEXT,"
                + KEY_HASFRUITPLANTS + " TEXT,"
                + KEY_HASNOFRUITPLANTS + " TEXT,"
                + KEY_HASVEGETABLECROPS + " TEXT,"
                + KEY_HASNOVEGETABLECROPS + " TEXT,"
                + KEY_LASTYEARINCOME + " TEXT,"
                + KEY_LASTYEAREXPENSE + " TEXT,"
                + KEY_WORKSABROAD + " TEXT,"
                + KEY_NOTWORKABROAD + " TEXT,"
                + KEY_REMITTANCESPENTON + " TEXT,"
                + KEY_LASTYEARINVESTMENTS + " TEXT,"
                + KEY_PRODUCTIONSSUSTAINABLEFOR + " TEXT,"
                + KEY_INCOMEISSUFFICIENT + " TEXT,"
                + KEY_INCOMEISNOTSUFFICIENT + " TEXT,"
                + KEY_ADDITIONALCASHSOURCE + " TEXT,"
                + KEY_HASTAKENLOAN + " TEXT,"
                + KEY_HASNOTTAKENLOAN + " TEXT,"
                + KEY_LOANSOURCE + " TEXT,"
                + KEY_TAKENLOANFOR + " TEXT,"
                + KEY_DURATIONTOCLEARLOAN + " TEXT,"
                + KEY_KNOWSSAMHIT + " TEXT,"
                + KEY_NOTKNOWSAMHIT + " TEXT,"
                + KEY_BUILTASPERSAMHIT + " TEXT,"
                + KEY_NOTBUILTASPERSAMHIT + " TEXT,"
                + KEY_NOTKNOWBUILTASPERSAMHIT + " TEXT,"
                + KEY_HASPERMISSIONBLUEPRINT + " TEXT,"
                + KEY_HASNOPERMISSIONBLUEPRINT + " TEXT,"
                + KEY_HASSAFEZONENEARHOUSE + " TEXT,"
                + KEY_HASNOSAFEZONENEARHOUSE + " TEXT,"
                + KEY_HOUSESUSCEPTTOCALAMITY + " TEXT,"
                + KEY_HOUSENOSUSCEPTTOCALAMITY + " TEXT,"
                + KEY_INFOABOUTEARTHQUAKE + " TEXT,"
                + KEY_NOINFOABOUTEARTHQUAKE + " TEXT,"
                + KEY_KNOWSAFEZONEINHOME + " TEXT,"
                + KEY_KNOWSNOSAFEZONEINHOME + " TEXT,"
                + KEY_HASSUPPLIESFORSOE + " TEXT,"
                + KEY_HASNOSUPPLIESFORSOE + " TEXT,"
                + KEY_AFFECTEDBYCALAMITIESLIST + " TEXT,"
                + KEY_LASTMAJORDISASTER + " TEXT,"
                + KEY_HASASSETINSURANCE + " TEXT,"
                + KEY_HASNOASSETINSURANCE + " TEXT,"
                + KEY_ASSETSLISTWITHINSURANCE + " TEXT,"
                + KEY_STEPSTAKENTOMINIMIZEDISASTEREFFECTS + " TEXT,"
                + KEY_ISVIOLENCEVICTIM + " TEXT,"
                + KEY_ISNOTVIOLENCEVICTIM + " TEXT,"
                + KEY_DEATHCOUNT + " TEXT,"
                + KEY_INJURYCOUNT + " TEXT,"
                + KEY_MISSINGCOUNT + " TEXT,"
                + KEY_ASSETLOSSAMOUNTRS + " TEXT,"
                + KEY_ISSUED + " TEXT,"
                + KEY_ISNOTSUED + " TEXT,"
                + KEY_ISSHIFTED + " TEXT,"
                + KEY_ISNOTSHIFTED + " TEXT,"
                + KEY_ISSEXUALLYABUSE + " TEXT,"
                + KEY_ISNOTSEXUALLYABUSED + " TEXT,"
                + KEY_MEMBERMISSING + " TEXT,"
                + KEY_MEMBERNOTMISSING + " TEXT,"
                + KEY_FEELSSAFEINMUNICIPAL + " TEXT,"
                + KEY_FEELSNOSAFEINMUNICIPAL + " TEXT,"
                + KEY_REASONFEELSSAFE + " TEXT,"
                + KEY_REASONNOFEELSAFE + " TEXT,"
                + KEY_WARDNOWHERENOTFEELSAFE + " TEXT,"
                + KEY_REGIONNAMEWHERENOTFEELSAFE + " TEXT,"
                + KEY_HOMEMEMBERUPTOAGE16WORKS + " TEXT,"
                + KEY_NOHOMEMEMBERUPTOAGE16WORKS + " TEXT,"
                + KEY_UPTOAGE16WORINGBOYCOUNT + " TEXT,"
                + KEY_UPTOAGE16WORKINGGIRLCOUNT + " TEXT,"
                + KEY_MEMBERUPTOAGE16HIREDWORK + " TEXT,"
                + KEY_NOMEMBERUPTOAGE16HIREDWORK + " TEXT,"
                + KEY_UPTOAGE16HIREDBOYCOUNT + " TEXT,"
                + KEY_UPTOAGE16HIREDGIRLCOUNT + " TEXT,"
                + KEY_CHILDRENISONBADINFLUENCE + " TEXT,"
                + KEY_CHILDRENNOTONBADINFLUENCE + " TEXT,"
                + KEY_CHILDRENBADINFLUENCEON + " TEXT,"
                + KEY_WAYOFMAKINGFAMILYDECISION + " TEXT,"
                + KEY_ASSETSONFEMALENAME + " TEXT,"
                + KEY_ALLOWANCELISTTAKENBYFAMILY + " TEXT,"
                + KEY_MEMBERHASINVOLVEDONDEVELOPMENT + " TEXT,"
                + KEY_MEMBERHASNOTINVOLVEDONDEVELOPMENT + " TEXT,"
                + KEY_OPINIONONDEVELOPMENTPRIORITY + " TEXT,"
                + KEY_ANNABALI_MURI + " TEXT,"
                + KEY_DHAN_MURI + " TEXT,"
                + KEY_MAKAI_MURI + " TEXT,"
                + KEY_KODO_MURI + " TEXT,"
                + KEY_GHAU_MURI + " TEXT,"
                + KEY_FAPAR_MURI + " TEXT,"
                + KEY_OTHERS_MURI + " TEXT,"
                + KEY_OIL_KG + " TEXT,"
                + KEY_DAAL_KG + " TEXT,"
                + KEY_TARKARI_KG + " TEXT,"
                + KEY_FRESHTARKARI_KG + " TEXT,"
                + KEY_AALU_KG + " TEXT,"
                + KEY_MASALA_KG + " TEXT,"
                + KEY_FALFUL_KG + " TEXT,"
                + KEY_KANDAMUL_KG + " TEXT,"
                + KEY_OTHERS_KG + " TEXT,"
                + KEY_COW_STHANIYA + " TEXT,"
                + KEY_COW_UNNAT + " TEXT,"
                + KEY_BUFFALO_STHANIYA + " TEXT,"
                + KEY_BUFFALO_UNNAT + " TEXT,"
                + KEY_GOAT_STHANIYA + " TEXT,"
                + KEY_GOAT_UNNAT + " TEXT,"
                + KEY_SHEEP_STHANIYA + " TEXT,"
                + KEY_SHEEP_UNNAT + " TEXT,"
                + KEY_PIG_STHANIYA + " TEXT,"
                + KEY_PIG_UNNAT + " TEXT,"
                + KEY_OTHER_ANIMAL + " TEXT,"
                + KEY_HEN_STHANIYA + " TEXT,"
                + KEY_HEN_UNNAT + " TEXT,"
                + KEY_PIGEON_COUNT + " TEXT,"
                + KEY_OTHER_BIRDS + " TEXT,"
                + KEY_FISH_COUNT + " TEXT,"
                + KEY_BEEHIVE_COUNT + " TEXT,"
                + KEY_OTHER_PASUPANCHI + " TEXT,"
                + KEY_MILKCURD_LITRE + " TEXT,"
                + KEY_GHEE_KG + " TEXT,"
                + KEY_OTHERDAIRY_KG + " TEXT,"
                + KEY_MEAT_KG + " TEXT,"
                + KEY_COMPOST_QUINTAL + " TEXT,"
                + KEY_URINE_LITRE + " TEXT,"
                + KEY_WOOL_KG + " TEXT,"
                + KEY_EGG_CRATE + " TEXT,"
                + KEY_FISH_KG + " TEXT,"
                + KEY_HONEY_KG + " TEXT,"
                + KEY_OTHER_PRODUCTION_KG + " TEXT,"
                + KEY_INCOMEAGRICULTURE + " TEXT,"
                + KEY_INCOMEBUSINESS + " TEXT,"
                + KEY_INCOMESALARYPENSION + " TEXT,"
                + KEY_INCOMESOCIALALLOWANCE + " TEXT,"
                + KEY_INCOMEFOREIGNEMP + " TEXT,"
                + KEY_INCOMEWAGES + " TEXT,"
                + KEY_INCOMERENT + " TEXT,"
                + KEY_INCOMEINTERESTINVEST + " TEXT,"
                + KEY_INCOMEOTHERS + " TEXT,"
                + KEY_EXPENSEFOOD + " TEXT,"
                + KEY_EXPENSECLOTH + " TEXT,"
                + KEY_EXPENSEEDUCATION + " TEXT,"
                + KEY_EXPENSEHEALTH + " TEXT,"
                + KEY_EXPENSEENTERTAIN + " TEXT,"
                + KEY_EXPENSERENT + " TEXT,"
                + KEY_EXPENSEAGRICULTURE + " TEXT,"
                + KEY_EXPENSEINSTALLMENT + " TEXT,"
                + KEY_EXPENSEFUEL + " TEXT,"
                + KEY_EXPENSETRANPORT + " TEXT,"
                + KEY_EXPENSEOTHERS + " TEXT,"

                + KEY_MEMBER_0_NAME + " TEXT,"
                + KEY_MEMBER_0_CAST + " TEXT,"
                + KEY_MEMBER_0_GENDER + " TEXT,"
                + KEY_MEMBER_0_AGE + " TEXT,"
                + KEY_MEMBER_0_RELATIONTOOWNER + " TEXT,"
                + KEY_MEMBER_0_BIRTHPLACE + " TEXT,"
                + KEY_MEMBER_0_HASEMAIL + " TEXT,"
                + KEY_MEMBER_0_HASNOEMAIL + " TEXT,"
                + KEY_MEMBER_0_HASLEFTHOME6MONTH + " TEXT,"
                + KEY_MEMBER_0_HASNOTLEFTHOME6MONTH + " TEXT,"
                + KEY_MEMBER_0_LEAVEHOME_PLACE + " TEXT,"
                + KEY_MEMBER_0_LEAVEHOME_REASON + " TEXT,"
                + KEY_MEMBER_0_EDUCATION + " TEXT,"
                + KEY_MEMBER_0_SCHOOLTYPE + " TEXT,"
                + KEY_MEMBER_0_LEAVESCHOOL_REASON + " TEXT,"
                + KEY_MEMBER_0_SCHOOLLEVEL + " TEXT,"
                + KEY_MEMBER_0_INCOMESOURCE + " TEXT,"
                + KEY_MEMBER_0_ABROAD_COUNTRY + " TEXT,"
                + KEY_MEMBER_0_ABROAD_MONEYTRANSFER + " TEXT,"
                + KEY_MEMBER_0_SKILLS + " TEXT,"
                + KEY_MEMBER_0_HASBANKAC + " TEXT,"
                + KEY_MEMBER_0_HASNOBANKAC + " TEXT,"
                + KEY_MEMBER_0_HASATM + " TEXT,"
                + KEY_MEMBER_0_HASNOATM + " TEXT,"
                + KEY_MEMBER_0_USEONLINEBANKING + " TEXT,"
                + KEY_MEMBER_0_NOTUSEONLINEBANKING + " TEXT,"
                + KEY_MEMBER_0_REGULARDEPOSIT + " TEXT,"
                + KEY_MEMBER_0_NOTREGULARDEPOSIT + " TEXT,"
                + KEY_MEMBER_0_REGULARDEPOSIT_TO + " TEXT,"
                + KEY_MEMBER_0_ISHEALTHY + " TEXT,"
                + KEY_MEMBER_0_ISDISABLED + " TEXT,"
                + KEY_MEMBER_0_DISABILITYTYPE + " TEXT,"
                + KEY_MEMBER_0_HASDISABILITYCARD + " TEXT,"
                + KEY_MEMBER_0_HASNODISABILITYCARD + " TEXT,"
                + KEY_MEMBER_0_HASDISEASE12MONTH + " TEXT,"
                + KEY_MEMBER_0_NODISEASE12MONTH + " TEXT,"
                + KEY_MEMBER_0_HASLONGTERMDISEASE + " TEXT,"
                + KEY_MEMBER_0_NOLONGTERMDISEASE + " TEXT,"
                + KEY_MEMBER_0_LONGTERMDISEASENAME + " TEXT,"
                + KEY_MEMBER_0_HASCOMMUNICABLEDISEASE + " TEXT,"
                + KEY_MEMBER_0_NOCOMMUNICABLEDISEASE + " TEXT,"
                + KEY_MEMBER_0_COMMUNICABLEDISEASENAME + " TEXT,"
                + KEY_MEMBER_0_USESVACCINE + " TEXT,"
                + KEY_MEMBER_0_SOCIALIDENTITY + " TEXT,"
                + KEY_MEMBER_0_SOCIALSECURITY_TYPE + " TEXT,"
                + KEY_MEMBER_0_MARITALSTATUS + " TEXT,"
                + KEY_MEMBER_0_SOCIALINVOLVEMENTS + " TEXT,"
                + KEY_MEMBER_0_HASRECEIVEDTRAINING + " TEXT,"
                + KEY_MEMBER_0_NOTRECEIVEDTRAINING + " TEXT,"
                + KEY_MEMBER_0_TRAININGLIST + " TEXT,"
                + KEY_MEMBER_0_ISPOLITICALINFLUENCER + " TEXT,"
                + KEY_MEMBER_0_ISNOTPOLITICALINFLUENCER + " TEXT,"
                + KEY_MEMBER_0_POLITICALINFLUENCERLEVEL + " TEXT,"
                + KEY_MEMBER_0_TRAVELWORK + " TEXT,"
                + KEY_MEMBER_0_TRAVELBUSINESS + " TEXT,"
                + KEY_MEMBER_0_TRAVELEDUCATION + " TEXT,"
                + KEY_MEMBER_0_TRAVELOTHERS + " TEXT,"

                + KEY_MEMBER_1_NAME + " TEXT,"
                + KEY_MEMBER_1_CAST + " TEXT,"
                + KEY_MEMBER_1_GENDER + " TEXT,"
                + KEY_MEMBER_1_AGE + " TEXT,"
                + KEY_MEMBER_1_RELATIONTOOWNER + " TEXT,"
                + KEY_MEMBER_1_BIRTHPLACE + " TEXT,"
                + KEY_MEMBER_1_HASEMAIL + " TEXT,"
                + KEY_MEMBER_1_HASNOEMAIL + " TEXT,"
                + KEY_MEMBER_1_HASLEFTHOME6MONTH + " TEXT,"
                + KEY_MEMBER_1_HASNOTLEFTHOME6MONTH + " TEXT,"
                + KEY_MEMBER_1_LEAVEHOME_PLACE + " TEXT,"
                + KEY_MEMBER_1_LEAVEHOME_REASON + " TEXT,"
                + KEY_MEMBER_1_EDUCATION + " TEXT,"
                + KEY_MEMBER_1_SCHOOLTYPE + " TEXT,"
                + KEY_MEMBER_1_LEAVESCHOOL_REASON + " TEXT,"
                + KEY_MEMBER_1_SCHOOLLEVEL + " TEXT,"
                + KEY_MEMBER_1_INCOMESOURCE + " TEXT,"
                + KEY_MEMBER_1_ABROAD_COUNTRY + " TEXT,"
                + KEY_MEMBER_1_ABROAD_MONEYTRANSFER + " TEXT,"
                + KEY_MEMBER_1_SKILLS + " TEXT,"
                + KEY_MEMBER_1_HASBANKAC + " TEXT,"
                + KEY_MEMBER_1_HASNOBANKAC + " TEXT,"
                + KEY_MEMBER_1_HASATM + " TEXT,"
                + KEY_MEMBER_1_HASNOATM + " TEXT,"
                + KEY_MEMBER_1_USEONLINEBANKING + " TEXT,"
                + KEY_MEMBER_1_NOTUSEONLINEBANKING + " TEXT,"
                + KEY_MEMBER_1_REGULARDEPOSIT + " TEXT,"
                + KEY_MEMBER_1_NOTREGULARDEPOSIT + " TEXT,"
                + KEY_MEMBER_1_REGULARDEPOSIT_TO + " TEXT,"
                + KEY_MEMBER_1_ISHEALTHY + " TEXT,"
                + KEY_MEMBER_1_ISDISABLED + " TEXT,"
                + KEY_MEMBER_1_DISABILITYTYPE + " TEXT,"
                + KEY_MEMBER_1_HASDISABILITYCARD + " TEXT,"
                + KEY_MEMBER_1_HASNODISABILITYCARD + " TEXT,"
                + KEY_MEMBER_1_HASDISEASE12MONTH + " TEXT,"
                + KEY_MEMBER_1_NODISEASE12MONTH + " TEXT,"
                + KEY_MEMBER_1_HASLONGTERMDISEASE + " TEXT,"
                + KEY_MEMBER_1_NOLONGTERMDISEASE + " TEXT,"
                + KEY_MEMBER_1_LONGTERMDISEASENAME + " TEXT,"
                + KEY_MEMBER_1_HASCOMMUNICABLEDISEASE + " TEXT,"
                + KEY_MEMBER_1_NOCOMMUNICABLEDISEASE + " TEXT,"
                + KEY_MEMBER_1_COMMUNICABLEDISEASENAME + " TEXT,"
                + KEY_MEMBER_1_USESVACCINE + " TEXT,"
                + KEY_MEMBER_1_SOCIALIDENTITY + " TEXT,"
                + KEY_MEMBER_1_SOCIALSECURITY_TYPE + " TEXT,"
                + KEY_MEMBER_1_MARITALSTATUS + " TEXT,"
                + KEY_MEMBER_1_SOCIALINVOLVEMENTS + " TEXT,"
                + KEY_MEMBER_1_HASRECEIVEDTRAINING + " TEXT,"
                + KEY_MEMBER_1_NOTRECEIVEDTRAINING + " TEXT,"
                + KEY_MEMBER_1_TRAININGLIST + " TEXT,"
                + KEY_MEMBER_1_ISPOLITICALINFLUENCER + " TEXT,"
                + KEY_MEMBER_1_ISNOTPOLITICALINFLUENCER + " TEXT,"
                + KEY_MEMBER_1_POLITICALINFLUENCERLEVEL + " TEXT,"
                + KEY_MEMBER_1_TRAVELWORK + " TEXT,"
                + KEY_MEMBER_1_TRAVELBUSINESS + " TEXT,"
                + KEY_MEMBER_1_TRAVELEDUCATION + " TEXT,"
                + KEY_MEMBER_1_TRAVELOTHERS + " TEXT,"

                + KEY_MEMBER_2_NAME + " TEXT,"
                + KEY_MEMBER_2_CAST + " TEXT,"
                + KEY_MEMBER_2_GENDER + " TEXT,"
                + KEY_MEMBER_2_AGE + " TEXT,"
                + KEY_MEMBER_2_RELATIONTOOWNER + " TEXT,"
                + KEY_MEMBER_2_BIRTHPLACE + " TEXT,"
                + KEY_MEMBER_2_HASEMAIL + " TEXT,"
                + KEY_MEMBER_2_HASNOEMAIL + " TEXT,"
                + KEY_MEMBER_2_HASLEFTHOME6MONTH + " TEXT,"
                + KEY_MEMBER_2_HASNOTLEFTHOME6MONTH + " TEXT,"
                + KEY_MEMBER_2_LEAVEHOME_PLACE + " TEXT,"
                + KEY_MEMBER_2_LEAVEHOME_REASON + " TEXT,"
                + KEY_MEMBER_2_EDUCATION + " TEXT,"
                + KEY_MEMBER_2_SCHOOLTYPE + " TEXT,"
                + KEY_MEMBER_2_LEAVESCHOOL_REASON + " TEXT,"
                + KEY_MEMBER_2_SCHOOLLEVEL + " TEXT,"
                + KEY_MEMBER_2_INCOMESOURCE + " TEXT,"
                + KEY_MEMBER_2_ABROAD_COUNTRY + " TEXT,"
                + KEY_MEMBER_2_ABROAD_MONEYTRANSFER + " TEXT,"
                + KEY_MEMBER_2_SKILLS + " TEXT,"
                + KEY_MEMBER_2_HASBANKAC + " TEXT,"
                + KEY_MEMBER_2_HASNOBANKAC + " TEXT,"
                + KEY_MEMBER_2_HASATM + " TEXT,"
                + KEY_MEMBER_2_HASNOATM + " TEXT,"
                + KEY_MEMBER_2_USEONLINEBANKING + " TEXT,"
                + KEY_MEMBER_2_NOTUSEONLINEBANKING + " TEXT,"
                + KEY_MEMBER_2_REGULARDEPOSIT + " TEXT,"
                + KEY_MEMBER_2_NOTREGULARDEPOSIT + " TEXT,"
                + KEY_MEMBER_2_REGULARDEPOSIT_TO + " TEXT,"
                + KEY_MEMBER_2_ISHEALTHY + " TEXT,"
                + KEY_MEMBER_2_ISDISABLED + " TEXT,"
                + KEY_MEMBER_2_DISABILITYTYPE + " TEXT,"
                + KEY_MEMBER_2_HASDISABILITYCARD + " TEXT,"
                + KEY_MEMBER_2_HASNODISABILITYCARD + " TEXT,"
                + KEY_MEMBER_2_HASDISEASE12MONTH + " TEXT,"
                + KEY_MEMBER_2_NODISEASE12MONTH + " TEXT,"
                + KEY_MEMBER_2_HASLONGTERMDISEASE + " TEXT,"
                + KEY_MEMBER_2_NOLONGTERMDISEASE + " TEXT,"
                + KEY_MEMBER_2_LONGTERMDISEASENAME + " TEXT,"
                + KEY_MEMBER_2_HASCOMMUNICABLEDISEASE + " TEXT,"
                + KEY_MEMBER_2_NOCOMMUNICABLEDISEASE + " TEXT,"
                + KEY_MEMBER_2_COMMUNICABLEDISEASENAME + " TEXT,"
                + KEY_MEMBER_2_USESVACCINE + " TEXT,"
                + KEY_MEMBER_2_SOCIALIDENTITY + " TEXT,"
                + KEY_MEMBER_2_SOCIALSECURITY_TYPE + " TEXT,"
                + KEY_MEMBER_2_MARITALSTATUS + " TEXT,"
                + KEY_MEMBER_2_SOCIALINVOLVEMENTS + " TEXT,"
                + KEY_MEMBER_2_HASRECEIVEDTRAINING + " TEXT,"
                + KEY_MEMBER_2_NOTRECEIVEDTRAINING + " TEXT,"
                + KEY_MEMBER_2_TRAININGLIST + " TEXT,"
                + KEY_MEMBER_2_ISPOLITICALINFLUENCER + " TEXT,"
                + KEY_MEMBER_2_ISNOTPOLITICALINFLUENCER + " TEXT,"
                + KEY_MEMBER_2_POLITICALINFLUENCERLEVEL + " TEXT,"
                + KEY_MEMBER_2_TRAVELWORK + " TEXT,"
                + KEY_MEMBER_2_TRAVELBUSINESS + " TEXT,"
                + KEY_MEMBER_2_TRAVELEDUCATION + " TEXT,"
                + KEY_MEMBER_2_TRAVELOTHERS + " TEXT,"

                + KEY_MEMBER_3_NAME + " TEXT,"
                + KEY_MEMBER_3_CAST + " TEXT,"
                + KEY_MEMBER_3_GENDER + " TEXT,"
                + KEY_MEMBER_3_AGE + " TEXT,"
                + KEY_MEMBER_3_RELATIONTOOWNER + " TEXT,"
                + KEY_MEMBER_3_BIRTHPLACE + " TEXT,"
                + KEY_MEMBER_3_HASEMAIL + " TEXT,"
                + KEY_MEMBER_3_HASNOEMAIL + " TEXT,"
                + KEY_MEMBER_3_HASLEFTHOME6MONTH + " TEXT,"
                + KEY_MEMBER_3_HASNOTLEFTHOME6MONTH + " TEXT,"
                + KEY_MEMBER_3_LEAVEHOME_PLACE + " TEXT,"
                + KEY_MEMBER_3_LEAVEHOME_REASON + " TEXT,"
                + KEY_MEMBER_3_EDUCATION + " TEXT,"
                + KEY_MEMBER_3_SCHOOLTYPE + " TEXT,"
                + KEY_MEMBER_3_LEAVESCHOOL_REASON + " TEXT,"
                + KEY_MEMBER_3_SCHOOLLEVEL + " TEXT,"
                + KEY_MEMBER_3_INCOMESOURCE + " TEXT,"
                + KEY_MEMBER_3_ABROAD_COUNTRY + " TEXT,"
                + KEY_MEMBER_3_ABROAD_MONEYTRANSFER + " TEXT,"
                + KEY_MEMBER_3_SKILLS + " TEXT,"
                + KEY_MEMBER_3_HASBANKAC + " TEXT,"
                + KEY_MEMBER_3_HASNOBANKAC + " TEXT,"
                + KEY_MEMBER_3_HASATM + " TEXT,"
                + KEY_MEMBER_3_HASNOATM + " TEXT,"
                + KEY_MEMBER_3_USEONLINEBANKING + " TEXT,"
                + KEY_MEMBER_3_NOTUSEONLINEBANKING + " TEXT,"
                + KEY_MEMBER_3_REGULARDEPOSIT + " TEXT,"
                + KEY_MEMBER_3_NOTREGULARDEPOSIT + " TEXT,"
                + KEY_MEMBER_3_REGULARDEPOSIT_TO + " TEXT,"
                + KEY_MEMBER_3_ISHEALTHY + " TEXT,"
                + KEY_MEMBER_3_ISDISABLED + " TEXT,"
                + KEY_MEMBER_3_DISABILITYTYPE + " TEXT,"
                + KEY_MEMBER_3_HASDISABILITYCARD + " TEXT,"
                + KEY_MEMBER_3_HASNODISABILITYCARD + " TEXT,"
                + KEY_MEMBER_3_HASDISEASE12MONTH + " TEXT,"
                + KEY_MEMBER_3_NODISEASE12MONTH + " TEXT,"
                + KEY_MEMBER_3_HASLONGTERMDISEASE + " TEXT,"
                + KEY_MEMBER_3_NOLONGTERMDISEASE + " TEXT,"
                + KEY_MEMBER_3_LONGTERMDISEASENAME + " TEXT,"
                + KEY_MEMBER_3_HASCOMMUNICABLEDISEASE + " TEXT,"
                + KEY_MEMBER_3_NOCOMMUNICABLEDISEASE + " TEXT,"
                + KEY_MEMBER_3_COMMUNICABLEDISEASENAME + " TEXT,"
                + KEY_MEMBER_3_USESVACCINE + " TEXT,"
                + KEY_MEMBER_3_SOCIALIDENTITY + " TEXT,"
                + KEY_MEMBER_3_SOCIALSECURITY_TYPE + " TEXT,"
                + KEY_MEMBER_3_MARITALSTATUS + " TEXT,"
                + KEY_MEMBER_3_SOCIALINVOLVEMENTS + " TEXT,"
                + KEY_MEMBER_3_HASRECEIVEDTRAINING + " TEXT,"
                + KEY_MEMBER_3_NOTRECEIVEDTRAINING + " TEXT,"
                + KEY_MEMBER_3_TRAININGLIST + " TEXT,"
                + KEY_MEMBER_3_ISPOLITICALINFLUENCER + " TEXT,"
                + KEY_MEMBER_3_ISNOTPOLITICALINFLUENCER + " TEXT,"
                + KEY_MEMBER_3_POLITICALINFLUENCERLEVEL + " TEXT,"
                + KEY_MEMBER_3_TRAVELWORK + " TEXT,"
                + KEY_MEMBER_3_TRAVELBUSINESS + " TEXT,"
                + KEY_MEMBER_3_TRAVELEDUCATION + " TEXT,"
                + KEY_MEMBER_3_TRAVELOTHERS + " TEXT,"

                + KEY_MEMBER_4_NAME + " TEXT,"
                + KEY_MEMBER_4_CAST + " TEXT,"
                + KEY_MEMBER_4_GENDER + " TEXT,"
                + KEY_MEMBER_4_AGE + " TEXT,"
                + KEY_MEMBER_4_RELATIONTOOWNER + " TEXT,"
                + KEY_MEMBER_4_BIRTHPLACE + " TEXT,"
                + KEY_MEMBER_4_HASEMAIL + " TEXT,"
                + KEY_MEMBER_4_HASNOEMAIL + " TEXT,"
                + KEY_MEMBER_4_HASLEFTHOME6MONTH + " TEXT,"
                + KEY_MEMBER_4_HASNOTLEFTHOME6MONTH + " TEXT,"
                + KEY_MEMBER_4_LEAVEHOME_PLACE + " TEXT,"
                + KEY_MEMBER_4_LEAVEHOME_REASON + " TEXT,"
                + KEY_MEMBER_4_EDUCATION + " TEXT,"
                + KEY_MEMBER_4_SCHOOLTYPE + " TEXT,"
                + KEY_MEMBER_4_LEAVESCHOOL_REASON + " TEXT,"
                + KEY_MEMBER_4_SCHOOLLEVEL + " TEXT,"
                + KEY_MEMBER_4_INCOMESOURCE + " TEXT,"
                + KEY_MEMBER_4_ABROAD_COUNTRY + " TEXT,"
                + KEY_MEMBER_4_ABROAD_MONEYTRANSFER + " TEXT,"
                + KEY_MEMBER_4_SKILLS + " TEXT,"
                + KEY_MEMBER_4_HASBANKAC + " TEXT,"
                + KEY_MEMBER_4_HASNOBANKAC + " TEXT,"
                + KEY_MEMBER_4_HASATM + " TEXT,"
                + KEY_MEMBER_4_HASNOATM + " TEXT,"
                + KEY_MEMBER_4_USEONLINEBANKING + " TEXT,"
                + KEY_MEMBER_4_NOTUSEONLINEBANKING + " TEXT,"
                + KEY_MEMBER_4_REGULARDEPOSIT + " TEXT,"
                + KEY_MEMBER_4_NOTREGULARDEPOSIT + " TEXT,"
                + KEY_MEMBER_4_REGULARDEPOSIT_TO + " TEXT,"
                + KEY_MEMBER_4_ISHEALTHY + " TEXT,"
                + KEY_MEMBER_4_ISDISABLED + " TEXT,"
                + KEY_MEMBER_4_DISABILITYTYPE + " TEXT,"
                + KEY_MEMBER_4_HASDISABILITYCARD + " TEXT,"
                + KEY_MEMBER_4_HASNODISABILITYCARD + " TEXT,"
                + KEY_MEMBER_4_HASDISEASE12MONTH + " TEXT,"
                + KEY_MEMBER_4_NODISEASE12MONTH + " TEXT,"
                + KEY_MEMBER_4_HASLONGTERMDISEASE + " TEXT,"
                + KEY_MEMBER_4_NOLONGTERMDISEASE + " TEXT,"
                + KEY_MEMBER_4_LONGTERMDISEASENAME + " TEXT,"
                + KEY_MEMBER_4_HASCOMMUNICABLEDISEASE + " TEXT,"
                + KEY_MEMBER_4_NOCOMMUNICABLEDISEASE + " TEXT,"
                + KEY_MEMBER_4_COMMUNICABLEDISEASENAME + " TEXT,"
                + KEY_MEMBER_4_USESVACCINE + " TEXT,"
                + KEY_MEMBER_4_SOCIALIDENTITY + " TEXT,"
                + KEY_MEMBER_4_SOCIALSECURITY_TYPE + " TEXT,"
                + KEY_MEMBER_4_MARITALSTATUS + " TEXT,"
                + KEY_MEMBER_4_SOCIALINVOLVEMENTS + " TEXT,"
                + KEY_MEMBER_4_HASRECEIVEDTRAINING + " TEXT,"
                + KEY_MEMBER_4_NOTRECEIVEDTRAINING + " TEXT,"
                + KEY_MEMBER_4_TRAININGLIST + " TEXT,"
                + KEY_MEMBER_4_ISPOLITICALINFLUENCER + " TEXT,"
                + KEY_MEMBER_4_ISNOTPOLITICALINFLUENCER + " TEXT,"
                + KEY_MEMBER_4_POLITICALINFLUENCERLEVEL + " TEXT,"
                + KEY_MEMBER_4_TRAVELWORK + " TEXT,"
                + KEY_MEMBER_4_TRAVELBUSINESS + " TEXT,"
                + KEY_MEMBER_4_TRAVELEDUCATION + " TEXT,"
                + KEY_MEMBER_4_TRAVELOTHERS + " TEXT,"

                + KEY_MEMBER_5_NAME + " TEXT,"
                + KEY_MEMBER_5_CAST + " TEXT,"
                + KEY_MEMBER_5_GENDER + " TEXT,"
                + KEY_MEMBER_5_AGE + " TEXT,"
                + KEY_MEMBER_5_RELATIONTOOWNER + " TEXT,"
                + KEY_MEMBER_5_BIRTHPLACE + " TEXT,"
                + KEY_MEMBER_5_HASEMAIL + " TEXT,"
                + KEY_MEMBER_5_HASNOEMAIL + " TEXT,"
                + KEY_MEMBER_5_HASLEFTHOME6MONTH + " TEXT,"
                + KEY_MEMBER_5_HASNOTLEFTHOME6MONTH + " TEXT,"
                + KEY_MEMBER_5_LEAVEHOME_PLACE + " TEXT,"
                + KEY_MEMBER_5_LEAVEHOME_REASON + " TEXT,"
                + KEY_MEMBER_5_EDUCATION + " TEXT,"
                + KEY_MEMBER_5_SCHOOLTYPE + " TEXT,"
                + KEY_MEMBER_5_LEAVESCHOOL_REASON + " TEXT,"
                + KEY_MEMBER_5_SCHOOLLEVEL + " TEXT,"
                + KEY_MEMBER_5_INCOMESOURCE + " TEXT,"
                + KEY_MEMBER_5_ABROAD_COUNTRY + " TEXT,"
                + KEY_MEMBER_5_ABROAD_MONEYTRANSFER + " TEXT,"
                + KEY_MEMBER_5_SKILLS + " TEXT,"
                + KEY_MEMBER_5_HASBANKAC + " TEXT,"
                + KEY_MEMBER_5_HASNOBANKAC + " TEXT,"
                + KEY_MEMBER_5_HASATM + " TEXT,"
                + KEY_MEMBER_5_HASNOATM + " TEXT,"
                + KEY_MEMBER_5_USEONLINEBANKING + " TEXT,"
                + KEY_MEMBER_5_NOTUSEONLINEBANKING + " TEXT,"
                + KEY_MEMBER_5_REGULARDEPOSIT + " TEXT,"
                + KEY_MEMBER_5_NOTREGULARDEPOSIT + " TEXT,"
                + KEY_MEMBER_5_REGULARDEPOSIT_TO + " TEXT,"
                + KEY_MEMBER_5_ISHEALTHY + " TEXT,"
                + KEY_MEMBER_5_ISDISABLED + " TEXT,"
                + KEY_MEMBER_5_DISABILITYTYPE + " TEXT,"
                + KEY_MEMBER_5_HASDISABILITYCARD + " TEXT,"
                + KEY_MEMBER_5_HASNODISABILITYCARD + " TEXT,"
                + KEY_MEMBER_5_HASDISEASE12MONTH + " TEXT,"
                + KEY_MEMBER_5_NODISEASE12MONTH + " TEXT,"
                + KEY_MEMBER_5_HASLONGTERMDISEASE + " TEXT,"
                + KEY_MEMBER_5_NOLONGTERMDISEASE + " TEXT,"
                + KEY_MEMBER_5_LONGTERMDISEASENAME + " TEXT,"
                + KEY_MEMBER_5_HASCOMMUNICABLEDISEASE + " TEXT,"
                + KEY_MEMBER_5_NOCOMMUNICABLEDISEASE + " TEXT,"
                + KEY_MEMBER_5_COMMUNICABLEDISEASENAME + " TEXT,"
                + KEY_MEMBER_5_USESVACCINE + " TEXT,"
                + KEY_MEMBER_5_SOCIALIDENTITY + " TEXT,"
                + KEY_MEMBER_5_SOCIALSECURITY_TYPE + " TEXT,"
                + KEY_MEMBER_5_MARITALSTATUS + " TEXT,"
                + KEY_MEMBER_5_SOCIALINVOLVEMENTS + " TEXT,"
                + KEY_MEMBER_5_HASRECEIVEDTRAINING + " TEXT,"
                + KEY_MEMBER_5_NOTRECEIVEDTRAINING + " TEXT,"
                + KEY_MEMBER_5_TRAININGLIST + " TEXT,"
                + KEY_MEMBER_5_ISPOLITICALINFLUENCER + " TEXT,"
                + KEY_MEMBER_5_ISNOTPOLITICALINFLUENCER + " TEXT,"
                + KEY_MEMBER_5_POLITICALINFLUENCERLEVEL + " TEXT,"
                + KEY_MEMBER_5_TRAVELWORK + " TEXT,"
                + KEY_MEMBER_5_TRAVELBUSINESS + " TEXT,"
                + KEY_MEMBER_5_TRAVELEDUCATION + " TEXT,"
                + KEY_MEMBER_5_TRAVELOTHERS + " TEXT,"

                + KEY_MEMBER_6_NAME + " TEXT,"
                + KEY_MEMBER_6_CAST + " TEXT,"
                + KEY_MEMBER_6_GENDER + " TEXT,"
                + KEY_MEMBER_6_AGE + " TEXT,"
                + KEY_MEMBER_6_RELATIONTOOWNER + " TEXT,"
                + KEY_MEMBER_6_BIRTHPLACE + " TEXT,"
                + KEY_MEMBER_6_HASEMAIL + " TEXT,"
                + KEY_MEMBER_6_HASNOEMAIL + " TEXT,"
                + KEY_MEMBER_6_HASLEFTHOME6MONTH + " TEXT,"
                + KEY_MEMBER_6_HASNOTLEFTHOME6MONTH + " TEXT,"
                + KEY_MEMBER_6_LEAVEHOME_PLACE + " TEXT,"
                + KEY_MEMBER_6_LEAVEHOME_REASON + " TEXT,"
                + KEY_MEMBER_6_EDUCATION + " TEXT,"
                + KEY_MEMBER_6_SCHOOLTYPE + " TEXT,"
                + KEY_MEMBER_6_LEAVESCHOOL_REASON + " TEXT,"
                + KEY_MEMBER_6_SCHOOLLEVEL + " TEXT,"
                + KEY_MEMBER_6_INCOMESOURCE + " TEXT,"
                + KEY_MEMBER_6_ABROAD_COUNTRY + " TEXT,"
                + KEY_MEMBER_6_ABROAD_MONEYTRANSFER + " TEXT,"
                + KEY_MEMBER_6_SKILLS + " TEXT,"
                + KEY_MEMBER_6_HASBANKAC + " TEXT,"
                + KEY_MEMBER_6_HASNOBANKAC + " TEXT,"
                + KEY_MEMBER_6_HASATM + " TEXT,"
                + KEY_MEMBER_6_HASNOATM + " TEXT,"
                + KEY_MEMBER_6_USEONLINEBANKING + " TEXT,"
                + KEY_MEMBER_6_NOTUSEONLINEBANKING + " TEXT,"
                + KEY_MEMBER_6_REGULARDEPOSIT + " TEXT,"
                + KEY_MEMBER_6_NOTREGULARDEPOSIT + " TEXT,"
                + KEY_MEMBER_6_REGULARDEPOSIT_TO + " TEXT,"
                + KEY_MEMBER_6_ISHEALTHY + " TEXT,"
                + KEY_MEMBER_6_ISDISABLED + " TEXT,"
                + KEY_MEMBER_6_DISABILITYTYPE + " TEXT,"
                + KEY_MEMBER_6_HASDISABILITYCARD + " TEXT,"
                + KEY_MEMBER_6_HASNODISABILITYCARD + " TEXT,"
                + KEY_MEMBER_6_HASDISEASE12MONTH + " TEXT,"
                + KEY_MEMBER_6_NODISEASE12MONTH + " TEXT,"
                + KEY_MEMBER_6_HASLONGTERMDISEASE + " TEXT,"
                + KEY_MEMBER_6_NOLONGTERMDISEASE + " TEXT,"
                + KEY_MEMBER_6_LONGTERMDISEASENAME + " TEXT,"
                + KEY_MEMBER_6_HASCOMMUNICABLEDISEASE + " TEXT,"
                + KEY_MEMBER_6_NOCOMMUNICABLEDISEASE + " TEXT,"
                + KEY_MEMBER_6_COMMUNICABLEDISEASENAME + " TEXT,"
                + KEY_MEMBER_6_USESVACCINE + " TEXT,"
                + KEY_MEMBER_6_SOCIALIDENTITY + " TEXT,"
                + KEY_MEMBER_6_SOCIALSECURITY_TYPE + " TEXT,"
                + KEY_MEMBER_6_MARITALSTATUS + " TEXT,"
                + KEY_MEMBER_6_SOCIALINVOLVEMENTS + " TEXT,"
                + KEY_MEMBER_6_HASRECEIVEDTRAINING + " TEXT,"
                + KEY_MEMBER_6_NOTRECEIVEDTRAINING + " TEXT,"
                + KEY_MEMBER_6_TRAININGLIST + " TEXT,"
                + KEY_MEMBER_6_ISPOLITICALINFLUENCER + " TEXT,"
                + KEY_MEMBER_6_ISNOTPOLITICALINFLUENCER + " TEXT,"
                + KEY_MEMBER_6_POLITICALINFLUENCERLEVEL + " TEXT,"
                + KEY_MEMBER_6_TRAVELWORK + " TEXT,"
                + KEY_MEMBER_6_TRAVELBUSINESS + " TEXT,"
                + KEY_MEMBER_6_TRAVELEDUCATION + " TEXT,"
                + KEY_MEMBER_6_TRAVELOTHERS + " TEXT,"

                + KEY_MEMBER_7_NAME + " TEXT,"
                + KEY_MEMBER_7_CAST + " TEXT,"
                + KEY_MEMBER_7_GENDER + " TEXT,"
                + KEY_MEMBER_7_AGE + " TEXT,"
                + KEY_MEMBER_7_RELATIONTOOWNER + " TEXT,"
                + KEY_MEMBER_7_BIRTHPLACE + " TEXT,"
                + KEY_MEMBER_7_HASEMAIL + " TEXT,"
                + KEY_MEMBER_7_HASNOEMAIL + " TEXT,"
                + KEY_MEMBER_7_HASLEFTHOME6MONTH + " TEXT,"
                + KEY_MEMBER_7_HASNOTLEFTHOME6MONTH + " TEXT,"
                + KEY_MEMBER_7_LEAVEHOME_PLACE + " TEXT,"
                + KEY_MEMBER_7_LEAVEHOME_REASON + " TEXT,"
                + KEY_MEMBER_7_EDUCATION + " TEXT,"
                + KEY_MEMBER_7_SCHOOLTYPE + " TEXT,"
                + KEY_MEMBER_7_LEAVESCHOOL_REASON + " TEXT,"
                + KEY_MEMBER_7_SCHOOLLEVEL + " TEXT,"
                + KEY_MEMBER_7_INCOMESOURCE + " TEXT,"
                + KEY_MEMBER_7_ABROAD_COUNTRY + " TEXT,"
                + KEY_MEMBER_7_ABROAD_MONEYTRANSFER + " TEXT,"
                + KEY_MEMBER_7_SKILLS + " TEXT,"
                + KEY_MEMBER_7_HASBANKAC + " TEXT,"
                + KEY_MEMBER_7_HASNOBANKAC + " TEXT,"
                + KEY_MEMBER_7_HASATM + " TEXT,"
                + KEY_MEMBER_7_HASNOATM + " TEXT,"
                + KEY_MEMBER_7_USEONLINEBANKING + " TEXT,"
                + KEY_MEMBER_7_NOTUSEONLINEBANKING + " TEXT,"
                + KEY_MEMBER_7_REGULARDEPOSIT + " TEXT,"
                + KEY_MEMBER_7_NOTREGULARDEPOSIT + " TEXT,"
                + KEY_MEMBER_7_REGULARDEPOSIT_TO + " TEXT,"
                + KEY_MEMBER_7_ISHEALTHY + " TEXT,"
                + KEY_MEMBER_7_ISDISABLED + " TEXT,"
                + KEY_MEMBER_7_DISABILITYTYPE + " TEXT,"
                + KEY_MEMBER_7_HASDISABILITYCARD + " TEXT,"
                + KEY_MEMBER_7_HASNODISABILITYCARD + " TEXT,"
                + KEY_MEMBER_7_HASDISEASE12MONTH + " TEXT,"
                + KEY_MEMBER_7_NODISEASE12MONTH + " TEXT,"
                + KEY_MEMBER_7_HASLONGTERMDISEASE + " TEXT,"
                + KEY_MEMBER_7_NOLONGTERMDISEASE + " TEXT,"
                + KEY_MEMBER_7_LONGTERMDISEASENAME + " TEXT,"
                + KEY_MEMBER_7_HASCOMMUNICABLEDISEASE + " TEXT,"
                + KEY_MEMBER_7_NOCOMMUNICABLEDISEASE + " TEXT,"
                + KEY_MEMBER_7_COMMUNICABLEDISEASENAME + " TEXT,"
                + KEY_MEMBER_7_USESVACCINE + " TEXT,"
                + KEY_MEMBER_7_SOCIALIDENTITY + " TEXT,"
                + KEY_MEMBER_7_SOCIALSECURITY_TYPE + " TEXT,"
                + KEY_MEMBER_7_MARITALSTATUS + " TEXT,"
                + KEY_MEMBER_7_SOCIALINVOLVEMENTS + " TEXT,"
                + KEY_MEMBER_7_HASRECEIVEDTRAINING + " TEXT,"
                + KEY_MEMBER_7_NOTRECEIVEDTRAINING + " TEXT,"
                + KEY_MEMBER_7_TRAININGLIST + " TEXT,"
                + KEY_MEMBER_7_ISPOLITICALINFLUENCER + " TEXT,"
                + KEY_MEMBER_7_ISNOTPOLITICALINFLUENCER + " TEXT,"
                + KEY_MEMBER_7_POLITICALINFLUENCERLEVEL + " TEXT,"
                + KEY_MEMBER_7_TRAVELWORK + " TEXT,"
                + KEY_MEMBER_7_TRAVELBUSINESS + " TEXT,"
                + KEY_MEMBER_7_TRAVELEDUCATION + " TEXT,"
                + KEY_MEMBER_7_TRAVELOTHERS + " TEXT,"

                + KEY_MEMBER_8_NAME + " TEXT,"
                + KEY_MEMBER_8_CAST + " TEXT,"
                + KEY_MEMBER_8_GENDER + " TEXT,"
                + KEY_MEMBER_8_AGE + " TEXT,"
                + KEY_MEMBER_8_RELATIONTOOWNER + " TEXT,"
                + KEY_MEMBER_8_BIRTHPLACE + " TEXT,"
                + KEY_MEMBER_8_HASEMAIL + " TEXT,"
                + KEY_MEMBER_8_HASNOEMAIL + " TEXT,"
                + KEY_MEMBER_8_HASLEFTHOME6MONTH + " TEXT,"
                + KEY_MEMBER_8_HASNOTLEFTHOME6MONTH + " TEXT,"
                + KEY_MEMBER_8_LEAVEHOME_PLACE + " TEXT,"
                + KEY_MEMBER_8_LEAVEHOME_REASON + " TEXT,"
                + KEY_MEMBER_8_EDUCATION + " TEXT,"
                + KEY_MEMBER_8_SCHOOLTYPE + " TEXT,"
                + KEY_MEMBER_8_LEAVESCHOOL_REASON + " TEXT,"
                + KEY_MEMBER_8_SCHOOLLEVEL + " TEXT,"
                + KEY_MEMBER_8_INCOMESOURCE + " TEXT,"
                + KEY_MEMBER_8_ABROAD_COUNTRY + " TEXT,"
                + KEY_MEMBER_8_ABROAD_MONEYTRANSFER + " TEXT,"
                + KEY_MEMBER_8_SKILLS + " TEXT,"
                + KEY_MEMBER_8_HASBANKAC + " TEXT,"
                + KEY_MEMBER_8_HASNOBANKAC + " TEXT,"
                + KEY_MEMBER_8_HASATM + " TEXT,"
                + KEY_MEMBER_8_HASNOATM + " TEXT,"
                + KEY_MEMBER_8_USEONLINEBANKING + " TEXT,"
                + KEY_MEMBER_8_NOTUSEONLINEBANKING + " TEXT,"
                + KEY_MEMBER_8_REGULARDEPOSIT + " TEXT,"
                + KEY_MEMBER_8_NOTREGULARDEPOSIT + " TEXT,"
                + KEY_MEMBER_8_REGULARDEPOSIT_TO + " TEXT,"
                + KEY_MEMBER_8_ISHEALTHY + " TEXT,"
                + KEY_MEMBER_8_ISDISABLED + " TEXT,"
                + KEY_MEMBER_8_DISABILITYTYPE + " TEXT,"
                + KEY_MEMBER_8_HASDISABILITYCARD + " TEXT,"
                + KEY_MEMBER_8_HASNODISABILITYCARD + " TEXT,"
                + KEY_MEMBER_8_HASDISEASE12MONTH + " TEXT,"
                + KEY_MEMBER_8_NODISEASE12MONTH + " TEXT,"
                + KEY_MEMBER_8_HASLONGTERMDISEASE + " TEXT,"
                + KEY_MEMBER_8_NOLONGTERMDISEASE + " TEXT,"
                + KEY_MEMBER_8_LONGTERMDISEASENAME + " TEXT,"
                + KEY_MEMBER_8_HASCOMMUNICABLEDISEASE + " TEXT,"
                + KEY_MEMBER_8_NOCOMMUNICABLEDISEASE + " TEXT,"
                + KEY_MEMBER_8_COMMUNICABLEDISEASENAME + " TEXT,"
                + KEY_MEMBER_8_USESVACCINE + " TEXT,"
                + KEY_MEMBER_8_SOCIALIDENTITY + " TEXT,"
                + KEY_MEMBER_8_SOCIALSECURITY_TYPE + " TEXT,"
                + KEY_MEMBER_8_MARITALSTATUS + " TEXT,"
                + KEY_MEMBER_8_SOCIALINVOLVEMENTS + " TEXT,"
                + KEY_MEMBER_8_HASRECEIVEDTRAINING + " TEXT,"
                + KEY_MEMBER_8_NOTRECEIVEDTRAINING + " TEXT,"
                + KEY_MEMBER_8_TRAININGLIST + " TEXT,"
                + KEY_MEMBER_8_ISPOLITICALINFLUENCER + " TEXT,"
                + KEY_MEMBER_8_ISNOTPOLITICALINFLUENCER + " TEXT,"
                + KEY_MEMBER_8_POLITICALINFLUENCERLEVEL + " TEXT,"
                + KEY_MEMBER_8_TRAVELWORK + " TEXT,"
                + KEY_MEMBER_8_TRAVELBUSINESS + " TEXT,"
                + KEY_MEMBER_8_TRAVELEDUCATION + " TEXT,"
                + KEY_MEMBER_8_TRAVELOTHERS + " TEXT,"

                + KEY_MEMBER_9_NAME + " TEXT,"
                + KEY_MEMBER_9_CAST + " TEXT,"
                + KEY_MEMBER_9_GENDER + " TEXT,"
                + KEY_MEMBER_9_AGE + " TEXT,"
                + KEY_MEMBER_9_RELATIONTOOWNER + " TEXT,"
                + KEY_MEMBER_9_BIRTHPLACE + " TEXT,"
                + KEY_MEMBER_9_HASEMAIL + " TEXT,"
                + KEY_MEMBER_9_HASNOEMAIL + " TEXT,"
                + KEY_MEMBER_9_HASLEFTHOME6MONTH + " TEXT,"
                + KEY_MEMBER_9_HASNOTLEFTHOME6MONTH + " TEXT,"
                + KEY_MEMBER_9_LEAVEHOME_PLACE + " TEXT,"
                + KEY_MEMBER_9_LEAVEHOME_REASON + " TEXT,"
                + KEY_MEMBER_9_EDUCATION + " TEXT,"
                + KEY_MEMBER_9_SCHOOLTYPE + " TEXT,"
                + KEY_MEMBER_9_LEAVESCHOOL_REASON + " TEXT,"
                + KEY_MEMBER_9_SCHOOLLEVEL + " TEXT,"
                + KEY_MEMBER_9_INCOMESOURCE + " TEXT,"
                + KEY_MEMBER_9_ABROAD_COUNTRY + " TEXT,"
                + KEY_MEMBER_9_ABROAD_MONEYTRANSFER + " TEXT,"
                + KEY_MEMBER_9_SKILLS + " TEXT,"
                + KEY_MEMBER_9_HASBANKAC + " TEXT,"
                + KEY_MEMBER_9_HASNOBANKAC + " TEXT,"
                + KEY_MEMBER_9_HASATM + " TEXT,"
                + KEY_MEMBER_9_HASNOATM + " TEXT,"
                + KEY_MEMBER_9_USEONLINEBANKING + " TEXT,"
                + KEY_MEMBER_9_NOTUSEONLINEBANKING + " TEXT,"
                + KEY_MEMBER_9_REGULARDEPOSIT + " TEXT,"
                + KEY_MEMBER_9_NOTREGULARDEPOSIT + " TEXT,"
                + KEY_MEMBER_9_REGULARDEPOSIT_TO + " TEXT,"
                + KEY_MEMBER_9_ISHEALTHY + " TEXT,"
                + KEY_MEMBER_9_ISDISABLED + " TEXT,"
                + KEY_MEMBER_9_DISABILITYTYPE + " TEXT,"
                + KEY_MEMBER_9_HASDISABILITYCARD + " TEXT,"
                + KEY_MEMBER_9_HASNODISABILITYCARD + " TEXT,"
                + KEY_MEMBER_9_HASDISEASE12MONTH + " TEXT,"
                + KEY_MEMBER_9_NODISEASE12MONTH + " TEXT,"
                + KEY_MEMBER_9_HASLONGTERMDISEASE + " TEXT,"
                + KEY_MEMBER_9_NOLONGTERMDISEASE + " TEXT,"
                + KEY_MEMBER_9_LONGTERMDISEASENAME + " TEXT,"
                + KEY_MEMBER_9_HASCOMMUNICABLEDISEASE + " TEXT,"
                + KEY_MEMBER_9_NOCOMMUNICABLEDISEASE + " TEXT,"
                + KEY_MEMBER_9_COMMUNICABLEDISEASENAME + " TEXT,"
                + KEY_MEMBER_9_USESVACCINE + " TEXT,"
                + KEY_MEMBER_9_SOCIALIDENTITY + " TEXT,"
                + KEY_MEMBER_9_SOCIALSECURITY_TYPE + " TEXT,"
                + KEY_MEMBER_9_MARITALSTATUS + " TEXT,"
                + KEY_MEMBER_9_SOCIALINVOLVEMENTS + " TEXT,"
                + KEY_MEMBER_9_HASRECEIVEDTRAINING + " TEXT,"
                + KEY_MEMBER_9_NOTRECEIVEDTRAINING + " TEXT,"
                + KEY_MEMBER_9_TRAININGLIST + " TEXT,"
                + KEY_MEMBER_9_ISPOLITICALINFLUENCER + " TEXT,"
                + KEY_MEMBER_9_ISNOTPOLITICALINFLUENCER + " TEXT,"
                + KEY_MEMBER_9_POLITICALINFLUENCERLEVEL + " TEXT,"
                + KEY_MEMBER_9_TRAVELWORK + " TEXT,"
                + KEY_MEMBER_9_TRAVELBUSINESS + " TEXT,"
                + KEY_MEMBER_9_TRAVELEDUCATION + " TEXT,"
                + KEY_MEMBER_9_TRAVELOTHERS + " TEXT"

                + ")";

        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    //CRUDE
    public void addDetail(Details details, Details2 details2, HouseholdData houseData, FamilyMemberData[] familyData) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(KEY_LAT, details.getLatitude());
        Log.d("HELLO2", details.getLatitude());
        values.put(KEY_LNG, details.getLongitude());
        values.put(KEY_ALT, details.getAltitude());
        values.put(KEY_PRADESH, details.getPradesh());
        values.put(KEY_JILLA, details.getJilla());
        values.put(KEY_NAGARPALIKA, details.getNagarpalika());
        values.put(KEY_WARD, details.getWard());
        Log.d("HELLOWARD", details.getWard());
        values.put(KEY_BASTI, details.getBasti());
        values.put(KEY_TOLENAME, details.getTole());
        values.put(KEY_SADAKNAME, details.getSadak());
        values.put(KEY_JATI, details.getJati());
        values.put(KEY_VASA, details.getVasa());
        values.put(KEY_DHARMA0, details.getDharma());
        values.put(KEY_GHARDHANINAME, details.getGhardhaniname());
        values.put(KEY_GHARDHANISEX, details.getGhardhanisex());
        values.put(KEY_GHARDHANIPHONE, details.getGhardhaniphone());
        Log.d("TESTJ", details.getJati());
        // Log.d("TESTV",details.getVasa());
        //Log.d("TESTD",details.getDharma());
        
        values.put(KEY_TENANT_FAMILY_COUNT, details2.getTenant_family_count());
        values.put(KEY_INFORMER_NAME, details2.getInformer_name());
        values.put(KEY_INFORMER_GENDER, details2.getInformer_gender());
        values.put(KEY_INFORMER_AGE, details2.getInformer_age());
        values.put(KEY_INFORMER_EMAIL, details2.getInformer_email());
        values.put(KEY_SWAMITTO, details2.getSwamitto());
        values.put(KEY_BASAI_ABADHI, details2.getBasai_abadhi());
        values.put(KEY_JATIYA_SAMUHA, details2.getJatiya_samuha());
        values.put(KEY_JATJATI, details2.getJatjati());
        values.put(KEY_DHARMA, details2.getDharma());
        values.put(KEY_MATRIBHASHA, details2.getMatribhasha());
        values.put(KEY_THAR, details2.getThar());
        values.put(KEY_PARIWAR_BASOBAS_AVASTHA, details2.getPariwar_basobas_avastha());
        values.put(KEY_BASAI_SARNU_KARAN, details2.getBasai_sarnu_karan());
        values.put(KEY_BASAI_AVADHI, details2.getBasai_avadhi());
        values.put(KEY_NAGARPALIKA_MA_GHAR_BHAYEKO, details2.getNagarpalika_ma_ghar_bhayeko());
        values.put(KEY_GHAR_JAGGA_SWAMITTO_KISIM, details2.getGhar_jagga_swamitto_kisim());
        values.put(KEY_NAGARPALIKA_MA_ANYA_GHAR_BHAYEKO, details2.getNagarpalika_ma_anya_ghar_bhayeko());
        values.put(KEY_ANYA_GHAR_K_KO_LAGI, details2.getAnya_ghar_k_ko_lagi());
        values.put(KEY_KITCHEN_ALAGGAI_BHAYEKO, details2.getKitchen_alaggai_bhayeko());
        values.put(KEY_KITCHEN_NIRMAN_AVASTHA, details2.getKitchen_nirman_avastha());
        values.put(KEY_KITCHEN_NIRMAN_BARSA, details2.getKitchen_nirman_barsa());
        values.put(KEY_BATTI_MAIN_SOURCE, details2.getBatti_main_source());
        values.put(KEY_ELECTRICITY_JADAN_BHAYEKO, details2.getElectricity_jadan_bhayeko());
        values.put(KEY_ELECTRICITY_JADAN_KINA_NABHAYEKO, details2.getElectricity_jadan_kina_nabhayeko());
        values.put(KEY_CULO_KO_PRAKAR, details2.getCulo_ko_prakar());
        values.put(KEY_PAKAUNE_FUEL, details2.getPakaune_fuel());
        values.put(KEY_DAAURA_KO_SOURCE, details2.getDaaura_ko_source());
        values.put(KEY_GAS_CYLINDER_TIKNE_DIN, details2.getGas_cylinder_tikne_din());
        values.put(KEY_OLD_CHULO_KO_LAGI_DAAURA_KG, details2.getOld_chulo_ko_lagi_daaura_kg());
        values.put(KEY_MODERN_CHULO_KO_LAGI_DAAURA_KG, details2.getModern_chulo_ko_lagi_daaura_kg());
        values.put(KEY_MATTITEL_PERMONTH_LITRE, details2.getMattitel_permonth_litre());
        values.put(KEY_INDUCTION_HEATER_HOURSPERDAY, details2.getInduction_heater_hoursperday());
        values.put(KEY_OVEN_HOURSPERDAY, details2.getOven_hoursperday());
        values.put(KEY_PAANI_TATAUNE_UPAKARAN, details2.getPaani_tataune_upakaran());
        values.put(KEY_PAANI_TATAUNA_CYLINDERPERYEAR, details2.getPaani_tatauna_cylinderperyear());
        values.put(KEY_PAANI_TATAUNA_WOOD_KGPERMONTH_OLD_CHULO, details2.getPaani_tatauna_wood_kgpermonth_old_chulo());
        values.put(KEY_PAANI_TATAUNA_WOOD_KGPERMONTH_MODERN, details2.getPaani_tatauna_wood_kgpermonth_modern());
        values.put(KEY_PAANI_TATAUNA_MATTITEL_PERMONTH, details2.getPaani_tatauna_mattitel_permonth());
        values.put(KEY_PAANI_TATAUNA_INDUCTION_HOURSPERDAY, details2.getPaani_tatauna_induction_hoursperday());
        values.put(KEY_PAANI_TATAUNA_OVEN_HOURSPERDAY, details2.getPaani_tatauna_oven_hoursperday());
        values.put(KEY_ROOM_HEATING_UPAKARAN, details2.getRoom_heating_upakaran());
        values.put(KEY_ROOM_COOLING_UPAKARAN, details2.getRoom_cooling_upakaran());
        values.put(KEY_FAN_COUNT, details2.getFan_count());
        values.put(KEY_FAN_WATT, details2.getFan_watt());
        values.put(KEY_FAN_HOURSPERDAY, details2.getFan_hoursperday());
        values.put(KEY_AIRCONDITION_COUNT, details2.getAircondition_count());
        values.put(KEY_AIRCONDITION_WATT, details2.getAircondition_watt());
        values.put(KEY_AIRCONDITION_HOURSPERDAY, details2.getAircondition_hoursperday());
        values.put(KEY_BIJULI_LOAD_AMPERE, details2.getBijuli_load_ampere());
        values.put(KEY_BIJULI_UNIT, details2.getBijuli_unit());
        values.put(KEY_WASHINGMACHINE_COUNT, details2.getWashingmachine_count());
        values.put(KEY_FRIDGE_COUNT, details2.getFridge_count());
        values.put(KEY_SOLAR_COUNT, details2.getSolar_count());
        values.put(KEY_VACUUM_COUNT, details2.getVacuum_count());
        values.put(KEY_INVERTER_COUNT, details2.getInverter_count());
        values.put(KEY_GENERATOR_COUNT, details2.getGenerator_count());
        values.put(KEY_DISHWASHER_COUNT, details2.getDishwasher_count());
        values.put(KEY_CABLE_COUNT, details2.getCable_count());
        values.put(KEY_OTHER_HOUSE_GADGET_COUNT, details2.getOther_house_gadget_count());
        values.put(KEY_RADIO_COUNT, details2.getRadio_count());
        values.put(KEY_TELEVISION_COUNT, details2.getTelevision_count());
        values.put(KEY_TELEPHONE_COUNT, details2.getTelephone_count());
        values.put(KEY_CELLPHONE_COUNT, details2.getCellphone_count());
        values.put(KEY_SMARTPHONE_COUNT, details2.getSmartphone_count());
        values.put(KEY_COMPUTER_LAPTOP_COUNT, details2.getComputer_laptop_count());
        values.put(KEY_INTERNET_COUNT, details2.getInternet_count());
        values.put(KEY_OTHER_COMMUNICATION_COUNT, details2.getOther_communication_count());
        values.put(KEY_INTERNET_SPEED_MBPS, details2.getInternet_speed_mbps());
        values.put(KEY_CYCLE_COUNT, details2.getCycle_count());
        values.put(KEY_BIKE_COUNT, details2.getBike_count());
        values.put(KEY_LIGHTWEIGHT_VEHICLE_COUNT, details2.getLightweight_vehicle_count());
        values.put(KEY_HEAVYWEIGHT_VEHICLE_COUNT, details2.getHeavyweight_vehicle_count());
        values.put(KEY_PUBLIC_TRANSPORT_VEHICLE_COUNT, details2.getPublic_transport_vehicle_count());
        values.put(KEY_FAMILY_MA_YATAYAT_LICENSE_BHAYEKO, details2.getFamily_ma_yatayat_license_bhayeko());
        values.put(KEY_LICENSE_BHAYEKO_COUNT, details2.getLicense_bhayeko_count());
        values.put(KEY_LICENSE_COUNT_2_WHEELER, details2.getLicense_count_2_wheeler());
        values.put(KEY_LICENSE_COUNT_4_WHEELER, details2.getLicense_count_4_wheeler());
        values.put(KEY_ONE_YEAR_MA_BIRAMI, details2.getOne_year_ma_birami());
        values.put(KEY_BIRAMI_HUDA_KATA_LAGEKO, details2.getBirami_huda_kata_lageko());
        values.put(KEY_HEALTHPOST_JANA_HIDERA_TIME, details2.getHealthpost_jana_hidera_time());
        values.put(KEY_HEALTHPOST_JANA_GAADI_TIME, details2.getHealthpost_jana_gaadi_time());
        values.put(KEY_TWO_YEAR_LE_VACCINE_LAGAYEKO, details2.getTwo_year_le_vaccine_lagayeko());
        values.put(KEY_VACCINE_NAGAREKO_KARAN, details2.getVaccine_nagareko_karan());
        values.put(KEY_TWO_YEAR_MA_PREGNANT, details2.getTwo_year_ma_pregnant());
        values.put(KEY_PREGNANT_KO_HEALTH_CHECKUP, details2.getPregnant_ko_health_checkup());
        values.put(KEY_PREGNANT_KO_CHECK_NAGARNE_KARAN, details2.getPregnant_ko_check_nagarne_karan());
        values.put(KEY_PREGNANT_KO_CHECK_GAREKO_COUNT, details2.getPregnant_ko_check_gareko_count());
        values.put(KEY_PREGNANT_LE_ICON_PILL_LINEGAREKO, details2.getPregnant_le_icon_pill_linegareko());
        values.put(KEY_PREGNANT_LE_JUKA_MEDICINE_LINEGAREKO, details2.getPregnant_le_juka_medicine_linegareko());
        values.put(KEY_PREGNANT_LE_VITAMIN_LINEGAREKO, details2.getPregnant_le_vitamin_linegareko());
        values.put(KEY_BACCHA_JANMAAUNE_STHAN, details2.getBaccha_janmaaune_sthan());
        values.put(KEY_BACCHA_JANMAAUNA_SAHAYOG_GARNE, details2.getBaccha_janmaauna_sahayog_garne());
        values.put(KEY_BACCHA_JANMAAUNA_HELP_NALINUKARAN, details2.getBaccha_janmaauna_help_nalinukaran());
        values.put(KEY_LAST12MONTHMA_UNDER_5YR_KO_DEATH, details2.getLast12Monthma_under_5yr_ko_death());
        values.put(KEY_UNDER_5YR_DEATH_GENDER, details2.getUnder_5yr_death_gender());
        values.put(KEY_UNDER_5YR_DEATH_AGE, details2.getUnder_5yr_death_age());
        values.put(KEY_UNDER_5YR_DEATH_KARAN, details2.getUnder_5yr_death_karan());
        values.put(KEY_UNDER_5YR_KO_DEVELOPMENT_MEASURE_HUNCHA, details2.getUnder_5yr_ko_development_measure_huncha());
        values.put(KEY_AGE6MONTH_TO_6YR_VITAMIN_A, details2.getAge6month_to_6yr_vitamin_A());
        values.put(KEY_AGE1YR_TO_6YR_JUKA_MEDICINE, details2.getAge1yr_to_6yr_juka_medicine());
        values.put(KEY_WINTER_MA_WARM_CLOTH, details2.getWinter_ma_warm_cloth());
        values.put(KEY_JHUUL_KO_BABYASTHA, details2.getJhuul_ko_babyastha());
        values.put(KEY_REGULAR_HEALTH_CHECKUP_PERYEAR, details2.getRegular_health_checkup_peryear());
        values.put(KEY_PAST12MONTH_MA_DEATH_BHAYEKO, details2.getPast12month_ma_death_bhayeko());
        values.put(KEY_YOG_RA_ADHYATMIK_KENDRA_MA_JANEGAREKO, details2.getYog_ra_adhyatmik_kendra_ma_janegareko());
        values.put(KEY_KHANEPAANI_SOURCE, details2.getKhanepaani_source());
        values.put(KEY_PAANI_METER_JADAN_GAREYEKO, details2.getPaani_meter_jadan_gareyeko());
        values.put(KEY_PAANI_SUFFICIENT_HUNE, details2.getPaani_sufficient_hune());
        values.put(KEY_PAANI_LINA_JANE_AAUNE_TIME, details2.getPaani_lina_jane_aaune_time());
        values.put(KEY_KHANEPAANI_QUALITY, details2.getKhanepaani_quality());
        values.put(KEY_PANI_PURIFY_GARNE_GAREKO, details2.getPani_purify_garne_gareko());
        values.put(KEY_RAIN_WATER_COLLECT_GAREKO, details2.getRain_water_collect_gareko());
        values.put(KEY_RAINWATER_KO_USE, details2.getRainwater_ko_use());
        values.put(KEY_GHAAR_MA_DHAAL_ATTACH_BHAYEKO, details2.getGhaar_ma_dhaal_attach_bhayeko());
        values.put(KEY_DHAAL_ATTACH_BHAYEKO_TYPE, details2.getDhaal_attach_bhayeko_type());
        values.put(KEY_WASTE_WATER_MANAGEMENT, details2.getWaste_water_management());
        values.put(KEY_DECOMPOSE_NODECOMPOSE_SEPARATE_GARNE, details2.getDecompose_nodecompose_separate_garne());
        values.put(KEY_SOLID_WASTE_MANAGEMENT, details2.getSolid_waste_management());


        values.put(KEY_HASTOILET, houseData.getHasToilet());
        values.put(KEY_HASNOTOILET, houseData.getHasNoToilet());
        values.put(KEY_TOILETTYPE, houseData.getToiletType());
        values.put(KEY_TOILETWASTEMGMT, houseData.getToiletWasteMgmt());
        values.put(KEY_ONLINESERVICES, houseData.getOnlineServices());
        values.put(KEY_PRIMARYINCOMESOURCE, houseData.getPrimaryIncomeSource());
        values.put(KEY_OWNSLAND, houseData.getOwnsLand());
        values.put(KEY_OWNSNOLAND, houseData.getOwnsNoLand());
        values.put(KEY_LANDLOCATION, houseData.getLandLocation());
        values.put(KEY_LANDOWNEDBY, houseData.getLandOwnedBy());
        values.put(KEY_LANDUSEDAS, houseData.getLandUsedAs());
        values.put(KEY_LANDONLEASE, houseData.getLandOnLease());
        values.put(KEY_LANDONNOLEASE, houseData.getLandOnNoLease());
        values.put(KEY_LANDONLEASEUSEDAS, houseData.getLandOnLeaseUsedAs());
        values.put(KEY_PRIMARYAGROPRODUCTIONS, houseData.getPrimaryAgroProductions());
        values.put(KEY_LASTYEARAGROPRODUCTIONS, houseData.getLastYearAgroProductions());
        values.put(KEY_ASSETANIMALS, houseData.getAssetAnimals());
        values.put(KEY_ASSETBIRDS, houseData.getAssetBirds());
        values.put(KEY_ASSETSFISHES, houseData.getAssetsFishes());
        values.put(KEY_ASSETSBEES, houseData.getAssetsBees());
        values.put(KEY_HORTICULTUREPRODUCTIONCOUNTS, houseData.getHorticultureProductionCounts());
        values.put(KEY_SELLSPRODUCTIONS, houseData.getSellsProductions());
        values.put(KEY_NOTSELLSPRODUCTIONS, houseData.getNotSellsProductions());
        values.put(KEY_SELLPRODUCTIONLIST, houseData.getSellProductionList());
        values.put(KEY_SELLPRODUCTIONTO, houseData.getSellProductionTo());
        values.put(KEY_HASFRUITPLANTS, houseData.getHasFruitPlants());
        values.put(KEY_HASNOFRUITPLANTS, houseData.getHasNoFruitPlants());
        values.put(KEY_HASVEGETABLECROPS, houseData.getHasVegetableCrops());
        values.put(KEY_HASNOVEGETABLECROPS, houseData.getHasNoVegetableCrops());
        values.put(KEY_LASTYEARINCOME, houseData.getLastYearIncome());
        values.put(KEY_LASTYEAREXPENSE, houseData.getLastYearExpense());
        values.put(KEY_WORKSABROAD, houseData.getWorksAbroad());
        values.put(KEY_NOTWORKABROAD, houseData.getNotWorkAbroad());
        values.put(KEY_REMITTANCESPENTON, houseData.getRemittanceSpentOn());
        values.put(KEY_LASTYEARINVESTMENTS, houseData.getLastYearInvestments());
        values.put(KEY_PRODUCTIONSSUSTAINABLEFOR, houseData.getProductionsSustainableFor());
        values.put(KEY_INCOMEISSUFFICIENT, houseData.getIncomeIsSufficient());
        values.put(KEY_INCOMEISNOTSUFFICIENT, houseData.getIncomeIsNotSufficient());
        values.put(KEY_ADDITIONALCASHSOURCE, houseData.getAdditionalCashSource());
        values.put(KEY_HASTAKENLOAN, houseData.getHasTakenLoan());
        values.put(KEY_HASNOTTAKENLOAN, houseData.getHasNotTakenLoan());
        values.put(KEY_LOANSOURCE, houseData.getLoanSource());
        values.put(KEY_TAKENLOANFOR, houseData.getTakenLoanFor());
        values.put(KEY_DURATIONTOCLEARLOAN, houseData.getDurationToClearLoan());
        values.put(KEY_KNOWSSAMHIT, houseData.getKnowsSamhit());
        values.put(KEY_NOTKNOWSAMHIT, houseData.getNotKnowSamhit());
        values.put(KEY_BUILTASPERSAMHIT, houseData.getBuiltAsPerSamhit());
        values.put(KEY_NOTBUILTASPERSAMHIT, houseData.getNotBuiltAsPerSamhit());
        values.put(KEY_NOTKNOWBUILTASPERSAMHIT, houseData.getNotKnowBuiltAsPerSamhit());
        values.put(KEY_HASPERMISSIONBLUEPRINT, houseData.getHasPermissionBlueprint());
        values.put(KEY_HASNOPERMISSIONBLUEPRINT, houseData.getHasNoPermissionBlueprint());
        values.put(KEY_HASSAFEZONENEARHOUSE, houseData.getHasSafeZoneNearHouse());
        values.put(KEY_HASNOSAFEZONENEARHOUSE, houseData.getHasNoSafeZoneNearHouse());
        values.put(KEY_HOUSESUSCEPTTOCALAMITY, houseData.getHouseSusceptToCalamity());
        values.put(KEY_HOUSENOSUSCEPTTOCALAMITY, houseData.getHouseNoSusceptToCalamity());
        values.put(KEY_INFOABOUTEARTHQUAKE, houseData.getInfoAboutEarthquake());
        values.put(KEY_NOINFOABOUTEARTHQUAKE, houseData.getNoInfoAboutEarthquake());
        values.put(KEY_KNOWSAFEZONEINHOME, houseData.getKnowSafeZoneInHome());
        values.put(KEY_KNOWSNOSAFEZONEINHOME, houseData.getKnowsNoSafeZoneInHome());
        values.put(KEY_HASSUPPLIESFORSOE, houseData.getHasSuppliesForSOE());
        values.put(KEY_HASNOSUPPLIESFORSOE, houseData.getHasNoSuppliesForSOE());
        values.put(KEY_AFFECTEDBYCALAMITIESLIST, houseData.getAffectedByCalamitiesList());
        values.put(KEY_LASTMAJORDISASTER, houseData.getLastMajorDisaster());
        values.put(KEY_HASASSETINSURANCE, houseData.getHasAssetInsurance());
        values.put(KEY_HASNOASSETINSURANCE, houseData.getHasNoAssetInsurance());
        values.put(KEY_ASSETSLISTWITHINSURANCE, houseData.getAssetsListWithInsurance());
        values.put(KEY_STEPSTAKENTOMINIMIZEDISASTEREFFECTS, houseData.getStepsTakenToMinimizeDisasterEffects());
        values.put(KEY_ISVIOLENCEVICTIM, houseData.getIsViolenceVictim());
        values.put(KEY_ISNOTVIOLENCEVICTIM, houseData.getIsNotViolenceVictim());
        values.put(KEY_DEATHCOUNT, houseData.getDeathCount());
        values.put(KEY_INJURYCOUNT, houseData.getInjuryCount());
        values.put(KEY_MISSINGCOUNT, houseData.getMissingCount());
        values.put(KEY_ASSETLOSSAMOUNTRS, houseData.getAssetLossAmountRs());
        values.put(KEY_ISSUED, houseData.getIsSued());
        values.put(KEY_ISNOTSUED, houseData.getIsNotSued());
        values.put(KEY_ISSHIFTED, houseData.getIsShifted());
        values.put(KEY_ISNOTSHIFTED, houseData.getIsNotShifted());
        values.put(KEY_ISSEXUALLYABUSE, houseData.getIsSexuallyAbuse());
        values.put(KEY_ISNOTSEXUALLYABUSED, houseData.getIsNotSexuallyAbused());
        values.put(KEY_MEMBERMISSING, houseData.getMemberMissing());
        values.put(KEY_MEMBERNOTMISSING, houseData.getMemberNotMissing());
        values.put(KEY_FEELSSAFEINMUNICIPAL, houseData.getFeelsSafeInMunicipal());
        values.put(KEY_FEELSNOSAFEINMUNICIPAL, houseData.getFeelsNoSafeInMunicipal());
        values.put(KEY_REASONFEELSSAFE, houseData.getReasonFeelsSafe());
        values.put(KEY_REASONNOFEELSAFE, houseData.getReasonNoFeelSafe());
        values.put(KEY_WARDNOWHERENOTFEELSAFE, houseData.getWardNoWhereNotFeelSafe());
        values.put(KEY_REGIONNAMEWHERENOTFEELSAFE, houseData.getRegionNameWhereNotFeelSafe());
        values.put(KEY_HOMEMEMBERUPTOAGE16WORKS, houseData.getHomeMemberUptoAge16Works());
        values.put(KEY_NOHOMEMEMBERUPTOAGE16WORKS, houseData.getNoHomeMemberUptoAge16Works());
        values.put(KEY_UPTOAGE16WORINGBOYCOUNT, houseData.getUptoAge16WoringBoyCount());
        values.put(KEY_UPTOAGE16WORKINGGIRLCOUNT, houseData.getUptoAge16WorkingGirlCount());
        values.put(KEY_MEMBERUPTOAGE16HIREDWORK, houseData.getMemberUptoAge16HiredWork());
        values.put(KEY_NOMEMBERUPTOAGE16HIREDWORK, houseData.getNoMemberUptoAge16HiredWork());
        values.put(KEY_UPTOAGE16HIREDBOYCOUNT, houseData.getUptoAge16HiredBoyCount());
        values.put(KEY_UPTOAGE16HIREDGIRLCOUNT, houseData.getUptoAge16HiredGirlCount());
        values.put(KEY_CHILDRENISONBADINFLUENCE, houseData.getChildrenIsOnBadInfluence());
        values.put(KEY_CHILDRENNOTONBADINFLUENCE, houseData.getChildrenNotOnBadInfluence());
        values.put(KEY_CHILDRENBADINFLUENCEON, houseData.getChildrenBadInfluenceOn());
        values.put(KEY_WAYOFMAKINGFAMILYDECISION, houseData.getWayOfMakingFamilyDecision());
        values.put(KEY_ASSETSONFEMALENAME, houseData.getAssetsOnFemaleName());
        values.put(KEY_ALLOWANCELISTTAKENBYFAMILY, houseData.getAllowanceListTakenByFamily());
        values.put(KEY_MEMBERHASINVOLVEDONDEVELOPMENT, houseData.getMemberHasInvolvedOnDevelopment());
        values.put(KEY_MEMBERHASNOTINVOLVEDONDEVELOPMENT, houseData.getMemberHasNotInvolvedOnDevelopment());
        values.put(KEY_OPINIONONDEVELOPMENTPRIORITY, houseData.getOpinionOnDevelopmentPriority());
        values.put(KEY_ANNABALI_MURI, houseData.getAnnabali_muri());
        values.put(KEY_DHAN_MURI, houseData.getDhan_muri());
        values.put(KEY_MAKAI_MURI, houseData.getMakai_muri());
        values.put(KEY_KODO_MURI, houseData.getKodo_muri());
        values.put(KEY_GHAU_MURI, houseData.getGhau_muri());
        values.put(KEY_FAPAR_MURI, houseData.getFapar_muri());
        values.put(KEY_OTHERS_MURI, houseData.getOthers_muri());
        values.put(KEY_OIL_KG, houseData.getOil_kg());
        values.put(KEY_DAAL_KG, houseData.getDaal_kg());
        values.put(KEY_TARKARI_KG, houseData.getTarkari_kg());
        values.put(KEY_FRESHTARKARI_KG, houseData.getFreshTarkari_kg());
        values.put(KEY_AALU_KG, houseData.getAalu_kg());
        values.put(KEY_MASALA_KG, houseData.getMasala_kg());
        values.put(KEY_FALFUL_KG, houseData.getFalful_kg());
        values.put(KEY_KANDAMUL_KG, houseData.getKandamul_kg());
        values.put(KEY_OTHERS_KG, houseData.getOthers_kg());
        values.put(KEY_COW_STHANIYA, houseData.getCow_sthaniya());
        values.put(KEY_COW_UNNAT, houseData.getCow_unnat());
        values.put(KEY_BUFFALO_STHANIYA, houseData.getBuffalo_sthaniya());
        values.put(KEY_BUFFALO_UNNAT, houseData.getBuffalo_unnat());
        values.put(KEY_GOAT_STHANIYA, houseData.getGoat_sthaniya());
        values.put(KEY_GOAT_UNNAT, houseData.getGoat_unnat());
        values.put(KEY_SHEEP_STHANIYA, houseData.getSheep_sthaniya());
        values.put(KEY_SHEEP_UNNAT, houseData.getSheep_unnat());
        values.put(KEY_PIG_STHANIYA, houseData.getPig_sthaniya());
        values.put(KEY_PIG_UNNAT, houseData.getPig_unnat());
        values.put(KEY_OTHER_ANIMAL, houseData.getOther_animal());
        values.put(KEY_HEN_STHANIYA, houseData.getHen_sthaniya());
        values.put(KEY_HEN_UNNAT, houseData.getHen_unnat());
        values.put(KEY_PIGEON_COUNT, houseData.getPigeon_count());
        values.put(KEY_OTHER_BIRDS, houseData.getOther_birds());
        values.put(KEY_FISH_COUNT, houseData.getFish_count());
        values.put(KEY_BEEHIVE_COUNT, houseData.getBeehive_count());
        values.put(KEY_OTHER_PASUPANCHI, houseData.getOther_pasupanchi());
        values.put(KEY_MILKCURD_LITRE, houseData.getMilkCurd_litre());
        values.put(KEY_GHEE_KG, houseData.getGhee_kg());
        values.put(KEY_OTHERDAIRY_KG, houseData.getOtherDairy_kg());
        values.put(KEY_MEAT_KG, houseData.getMeat_kg());
        values.put(KEY_COMPOST_QUINTAL, houseData.getCompost_quintal());
        values.put(KEY_URINE_LITRE, houseData.getUrine_litre());
        values.put(KEY_WOOL_KG, houseData.getWool_kg());
        values.put(KEY_EGG_CRATE, houseData.getEgg_crate());
        values.put(KEY_FISH_KG, houseData.getFish_kg());
        values.put(KEY_HONEY_KG, houseData.getHoney_kg());
        values.put(KEY_OTHER_PRODUCTION_KG, houseData.getOther_production_kg());
        values.put(KEY_INCOMEAGRICULTURE, houseData.getIncomeAgriculture());
        values.put(KEY_INCOMEBUSINESS, houseData.getIncomeBusiness());
        values.put(KEY_INCOMESALARYPENSION, houseData.getIncomeSalaryPension());
        values.put(KEY_INCOMESOCIALALLOWANCE, houseData.getIncomeSocialAllowance());
        values.put(KEY_INCOMEFOREIGNEMP, houseData.getIncomeForeignEmp());
        values.put(KEY_INCOMEWAGES, houseData.getIncomeWages());
        values.put(KEY_INCOMERENT, houseData.getIncomeRent());
        values.put(KEY_INCOMEINTERESTINVEST, houseData.getIncomeInterestInvest());
        values.put(KEY_INCOMEOTHERS, houseData.getIncomeOthers());
        values.put(KEY_EXPENSEFOOD, houseData.getExpenseFood());
        values.put(KEY_EXPENSECLOTH, houseData.getExpenseCloth());
        values.put(KEY_EXPENSEEDUCATION, houseData.getExpenseEducation());
        values.put(KEY_EXPENSEHEALTH, houseData.getExpenseHealth());
        values.put(KEY_EXPENSEENTERTAIN, houseData.getExpenseEntertain());
        values.put(KEY_EXPENSERENT, houseData.getExpenseRent());
        values.put(KEY_EXPENSEAGRICULTURE, houseData.getExpenseAgriculture());
        values.put(KEY_EXPENSEINSTALLMENT, houseData.getExpenseInstallment());
        values.put(KEY_EXPENSEFUEL, houseData.getExpenseFuel());
        values.put(KEY_EXPENSETRANPORT, houseData.getExpenseTranport());
        values.put(KEY_EXPENSEOTHERS, houseData.getExpenseOthers());

        values.put(KEY_MEMBER_0_NAME, familyData[0].getName());
        values.put(KEY_MEMBER_0_CAST, familyData[0].getCast());
        values.put(KEY_MEMBER_0_GENDER, familyData[0].getGender());
        values.put(KEY_MEMBER_0_AGE, familyData[0].getAge());
        values.put(KEY_MEMBER_0_RELATIONTOOWNER, familyData[0].getRelationToOwner());
        values.put(KEY_MEMBER_0_BIRTHPLACE, familyData[0].getBirthplace());
        values.put(KEY_MEMBER_0_HASEMAIL, familyData[0].getHasEmail());
        values.put(KEY_MEMBER_0_HASNOEMAIL, familyData[0].getHasNoEmail());
        values.put(KEY_MEMBER_0_HASLEFTHOME6MONTH, familyData[0].getHasLeftHome6Month());
        values.put(KEY_MEMBER_0_HASNOTLEFTHOME6MONTH, familyData[0].getHasNotLeftHome6Month());
        values.put(KEY_MEMBER_0_LEAVEHOME_PLACE, familyData[0].getLeaveHome_place());
        values.put(KEY_MEMBER_0_LEAVEHOME_REASON, familyData[0].getLeaveHome_reason());
        values.put(KEY_MEMBER_0_EDUCATION, familyData[0].getEducation());
        values.put(KEY_MEMBER_0_SCHOOLTYPE, familyData[0].getSchoolType());
        values.put(KEY_MEMBER_0_LEAVESCHOOL_REASON, familyData[0].getLeaveSchool_reason());
        values.put(KEY_MEMBER_0_SCHOOLLEVEL, familyData[0].getSchoolLevel());
        values.put(KEY_MEMBER_0_INCOMESOURCE, familyData[0].getIncomeSource());
        values.put(KEY_MEMBER_0_ABROAD_COUNTRY, familyData[0].getAbroad_country());
        values.put(KEY_MEMBER_0_ABROAD_MONEYTRANSFER, familyData[0].getAbroad_moneyTransfer());
        values.put(KEY_MEMBER_0_SKILLS, familyData[0].getSkills());
        values.put(KEY_MEMBER_0_HASBANKAC, familyData[0].getHasBankAC());
        values.put(KEY_MEMBER_0_HASNOBANKAC, familyData[0].getHasNoBankAC());
        values.put(KEY_MEMBER_0_HASATM, familyData[0].getHasATM());
        values.put(KEY_MEMBER_0_HASNOATM, familyData[0].getHasNoATM());
        values.put(KEY_MEMBER_0_USEONLINEBANKING, familyData[0].getUseOnlineBanking());
        values.put(KEY_MEMBER_0_NOTUSEONLINEBANKING, familyData[0].getNotUseOnlineBanking());
        values.put(KEY_MEMBER_0_REGULARDEPOSIT, familyData[0].getRegularDeposit());
        values.put(KEY_MEMBER_0_NOTREGULARDEPOSIT, familyData[0].getNotRegularDeposit());
        values.put(KEY_MEMBER_0_REGULARDEPOSIT_TO, familyData[0].getRegularDeposit_to());
        values.put(KEY_MEMBER_0_ISHEALTHY, familyData[0].getIsHealthy());
        values.put(KEY_MEMBER_0_ISDISABLED, familyData[0].getIsDisabled());
        values.put(KEY_MEMBER_0_DISABILITYTYPE, familyData[0].getDisabilityType());
        values.put(KEY_MEMBER_0_HASDISABILITYCARD, familyData[0].getHasDisabilityCard());
        values.put(KEY_MEMBER_0_HASNODISABILITYCARD, familyData[0].getHasNoDisabilityCard());
        values.put(KEY_MEMBER_0_HASDISEASE12MONTH, familyData[0].getHasDisease12Month());
        values.put(KEY_MEMBER_0_NODISEASE12MONTH, familyData[0].getNoDisease12Month());
        values.put(KEY_MEMBER_0_HASLONGTERMDISEASE, familyData[0].getHasLongTermDisease());
        values.put(KEY_MEMBER_0_NOLONGTERMDISEASE, familyData[0].getNoLongTermDisease());
        values.put(KEY_MEMBER_0_LONGTERMDISEASENAME, familyData[0].getLongTermDiseaseName());
        values.put(KEY_MEMBER_0_HASCOMMUNICABLEDISEASE, familyData[0].getHasCommunicableDisease());
        values.put(KEY_MEMBER_0_NOCOMMUNICABLEDISEASE, familyData[0].getNoCommunicableDisease());
        values.put(KEY_MEMBER_0_COMMUNICABLEDISEASENAME, familyData[0].getCommunicableDiseaseName());
        values.put(KEY_MEMBER_0_USESVACCINE, familyData[0].getUsesVaccine());
        values.put(KEY_MEMBER_0_SOCIALIDENTITY, familyData[0].getSocialIdentity());
        values.put(KEY_MEMBER_0_SOCIALSECURITY_TYPE, familyData[0].getSocialSecurity_type());
        values.put(KEY_MEMBER_0_MARITALSTATUS, familyData[0].getMaritalStatus());
        values.put(KEY_MEMBER_0_SOCIALINVOLVEMENTS, familyData[0].getSocialInvolvements());
        values.put(KEY_MEMBER_0_HASRECEIVEDTRAINING, familyData[0].getHasReceivedTraining());
        values.put(KEY_MEMBER_0_NOTRECEIVEDTRAINING, familyData[0].getNotReceivedTraining());
        values.put(KEY_MEMBER_0_TRAININGLIST, familyData[0].getTrainingList());
        values.put(KEY_MEMBER_0_ISPOLITICALINFLUENCER, familyData[0].getIsPoliticalInfluencer());
        values.put(KEY_MEMBER_0_ISNOTPOLITICALINFLUENCER, familyData[0].getIsNotPoliticalInfluencer());
        values.put(KEY_MEMBER_0_POLITICALINFLUENCERLEVEL, familyData[0].getPoliticalInfluencerLevel());
        values.put(KEY_MEMBER_0_TRAVELWORK, familyData[0].getTravelWork());
        values.put(KEY_MEMBER_0_TRAVELBUSINESS, familyData[0].getTravelBusiness());
        values.put(KEY_MEMBER_0_TRAVELEDUCATION, familyData[0].getTravelEducation());
        values.put(KEY_MEMBER_0_TRAVELOTHERS, familyData[0].getTravelOthers());

        values.put(KEY_MEMBER_1_NAME, familyData[1].getName());
        values.put(KEY_MEMBER_1_CAST, familyData[1].getCast());
        values.put(KEY_MEMBER_1_GENDER, familyData[1].getGender());
        values.put(KEY_MEMBER_1_AGE, familyData[1].getAge());
        values.put(KEY_MEMBER_1_RELATIONTOOWNER, familyData[1].getRelationToOwner());
        values.put(KEY_MEMBER_1_BIRTHPLACE, familyData[1].getBirthplace());
        values.put(KEY_MEMBER_1_HASEMAIL, familyData[1].getHasEmail());
        values.put(KEY_MEMBER_1_HASNOEMAIL, familyData[1].getHasNoEmail());
        values.put(KEY_MEMBER_1_HASLEFTHOME6MONTH, familyData[1].getHasLeftHome6Month());
        values.put(KEY_MEMBER_1_HASNOTLEFTHOME6MONTH, familyData[1].getHasNotLeftHome6Month());
        values.put(KEY_MEMBER_1_LEAVEHOME_PLACE, familyData[1].getLeaveHome_place());
        values.put(KEY_MEMBER_1_LEAVEHOME_REASON, familyData[1].getLeaveHome_reason());
        values.put(KEY_MEMBER_1_EDUCATION, familyData[1].getEducation());
        values.put(KEY_MEMBER_1_SCHOOLTYPE, familyData[1].getSchoolType());
        values.put(KEY_MEMBER_1_LEAVESCHOOL_REASON, familyData[1].getLeaveSchool_reason());
        values.put(KEY_MEMBER_1_SCHOOLLEVEL, familyData[1].getSchoolLevel());
        values.put(KEY_MEMBER_1_INCOMESOURCE, familyData[1].getIncomeSource());
        values.put(KEY_MEMBER_1_ABROAD_COUNTRY, familyData[1].getAbroad_country());
        values.put(KEY_MEMBER_1_ABROAD_MONEYTRANSFER, familyData[1].getAbroad_moneyTransfer());
        values.put(KEY_MEMBER_1_SKILLS, familyData[1].getSkills());
        values.put(KEY_MEMBER_1_HASBANKAC, familyData[1].getHasBankAC());
        values.put(KEY_MEMBER_1_HASNOBANKAC, familyData[1].getHasNoBankAC());
        values.put(KEY_MEMBER_1_HASATM, familyData[1].getHasATM());
        values.put(KEY_MEMBER_1_HASNOATM, familyData[1].getHasNoATM());
        values.put(KEY_MEMBER_1_USEONLINEBANKING, familyData[1].getUseOnlineBanking());
        values.put(KEY_MEMBER_1_NOTUSEONLINEBANKING, familyData[1].getNotUseOnlineBanking());
        values.put(KEY_MEMBER_1_REGULARDEPOSIT, familyData[1].getRegularDeposit());
        values.put(KEY_MEMBER_1_NOTREGULARDEPOSIT, familyData[1].getNotRegularDeposit());
        values.put(KEY_MEMBER_1_REGULARDEPOSIT_TO, familyData[1].getRegularDeposit_to());
        values.put(KEY_MEMBER_1_ISHEALTHY, familyData[1].getIsHealthy());
        values.put(KEY_MEMBER_1_ISDISABLED, familyData[1].getIsDisabled());
        values.put(KEY_MEMBER_1_DISABILITYTYPE, familyData[1].getDisabilityType());
        values.put(KEY_MEMBER_1_HASDISABILITYCARD, familyData[1].getHasDisabilityCard());
        values.put(KEY_MEMBER_1_HASNODISABILITYCARD, familyData[1].getHasNoDisabilityCard());
        values.put(KEY_MEMBER_1_HASDISEASE12MONTH, familyData[1].getHasDisease12Month());
        values.put(KEY_MEMBER_1_NODISEASE12MONTH, familyData[1].getNoDisease12Month());
        values.put(KEY_MEMBER_1_HASLONGTERMDISEASE, familyData[1].getHasLongTermDisease());
        values.put(KEY_MEMBER_1_NOLONGTERMDISEASE, familyData[1].getNoLongTermDisease());
        values.put(KEY_MEMBER_1_LONGTERMDISEASENAME, familyData[1].getLongTermDiseaseName());
        values.put(KEY_MEMBER_1_HASCOMMUNICABLEDISEASE, familyData[1].getHasCommunicableDisease());
        values.put(KEY_MEMBER_1_NOCOMMUNICABLEDISEASE, familyData[1].getNoCommunicableDisease());
        values.put(KEY_MEMBER_1_COMMUNICABLEDISEASENAME, familyData[1].getCommunicableDiseaseName());
        values.put(KEY_MEMBER_1_USESVACCINE, familyData[1].getUsesVaccine());
        values.put(KEY_MEMBER_1_SOCIALIDENTITY, familyData[1].getSocialIdentity());
        values.put(KEY_MEMBER_1_SOCIALSECURITY_TYPE, familyData[1].getSocialSecurity_type());
        values.put(KEY_MEMBER_1_MARITALSTATUS, familyData[1].getMaritalStatus());
        values.put(KEY_MEMBER_1_SOCIALINVOLVEMENTS, familyData[1].getSocialInvolvements());
        values.put(KEY_MEMBER_1_HASRECEIVEDTRAINING, familyData[1].getHasReceivedTraining());
        values.put(KEY_MEMBER_1_NOTRECEIVEDTRAINING, familyData[1].getNotReceivedTraining());
        values.put(KEY_MEMBER_1_TRAININGLIST, familyData[1].getTrainingList());
        values.put(KEY_MEMBER_1_ISPOLITICALINFLUENCER, familyData[1].getIsPoliticalInfluencer());
        values.put(KEY_MEMBER_1_ISNOTPOLITICALINFLUENCER, familyData[1].getIsNotPoliticalInfluencer());
        values.put(KEY_MEMBER_1_POLITICALINFLUENCERLEVEL, familyData[1].getPoliticalInfluencerLevel());
        values.put(KEY_MEMBER_1_TRAVELWORK, familyData[1].getTravelWork());
        values.put(KEY_MEMBER_1_TRAVELBUSINESS, familyData[1].getTravelBusiness());
        values.put(KEY_MEMBER_1_TRAVELEDUCATION, familyData[1].getTravelEducation());
        values.put(KEY_MEMBER_1_TRAVELOTHERS, familyData[1].getTravelOthers());

        values.put(KEY_MEMBER_2_NAME, familyData[2].getName());
        values.put(KEY_MEMBER_2_CAST, familyData[2].getCast());
        values.put(KEY_MEMBER_2_GENDER, familyData[2].getGender());
        values.put(KEY_MEMBER_2_AGE, familyData[2].getAge());
        values.put(KEY_MEMBER_2_RELATIONTOOWNER, familyData[2].getRelationToOwner());
        values.put(KEY_MEMBER_2_BIRTHPLACE, familyData[2].getBirthplace());
        values.put(KEY_MEMBER_2_HASEMAIL, familyData[2].getHasEmail());
        values.put(KEY_MEMBER_2_HASNOEMAIL, familyData[2].getHasNoEmail());
        values.put(KEY_MEMBER_2_HASLEFTHOME6MONTH, familyData[2].getHasLeftHome6Month());
        values.put(KEY_MEMBER_2_HASNOTLEFTHOME6MONTH, familyData[2].getHasNotLeftHome6Month());
        values.put(KEY_MEMBER_2_LEAVEHOME_PLACE, familyData[2].getLeaveHome_place());
        values.put(KEY_MEMBER_2_LEAVEHOME_REASON, familyData[2].getLeaveHome_reason());
        values.put(KEY_MEMBER_2_EDUCATION, familyData[2].getEducation());
        values.put(KEY_MEMBER_2_SCHOOLTYPE, familyData[2].getSchoolType());
        values.put(KEY_MEMBER_2_LEAVESCHOOL_REASON, familyData[2].getLeaveSchool_reason());
        values.put(KEY_MEMBER_2_SCHOOLLEVEL, familyData[2].getSchoolLevel());
        values.put(KEY_MEMBER_2_INCOMESOURCE, familyData[2].getIncomeSource());
        values.put(KEY_MEMBER_2_ABROAD_COUNTRY, familyData[2].getAbroad_country());
        values.put(KEY_MEMBER_2_ABROAD_MONEYTRANSFER, familyData[2].getAbroad_moneyTransfer());
        values.put(KEY_MEMBER_2_SKILLS, familyData[2].getSkills());
        values.put(KEY_MEMBER_2_HASBANKAC, familyData[2].getHasBankAC());
        values.put(KEY_MEMBER_2_HASNOBANKAC, familyData[2].getHasNoBankAC());
        values.put(KEY_MEMBER_2_HASATM, familyData[2].getHasATM());
        values.put(KEY_MEMBER_2_HASNOATM, familyData[2].getHasNoATM());
        values.put(KEY_MEMBER_2_USEONLINEBANKING, familyData[2].getUseOnlineBanking());
        values.put(KEY_MEMBER_2_NOTUSEONLINEBANKING, familyData[2].getNotUseOnlineBanking());
        values.put(KEY_MEMBER_2_REGULARDEPOSIT, familyData[2].getRegularDeposit());
        values.put(KEY_MEMBER_2_NOTREGULARDEPOSIT, familyData[2].getNotRegularDeposit());
        values.put(KEY_MEMBER_2_REGULARDEPOSIT_TO, familyData[2].getRegularDeposit_to());
        values.put(KEY_MEMBER_2_ISHEALTHY, familyData[2].getIsHealthy());
        values.put(KEY_MEMBER_2_ISDISABLED, familyData[2].getIsDisabled());
        values.put(KEY_MEMBER_2_DISABILITYTYPE, familyData[2].getDisabilityType());
        values.put(KEY_MEMBER_2_HASDISABILITYCARD, familyData[2].getHasDisabilityCard());
        values.put(KEY_MEMBER_2_HASNODISABILITYCARD, familyData[2].getHasNoDisabilityCard());
        values.put(KEY_MEMBER_2_HASDISEASE12MONTH, familyData[2].getHasDisease12Month());
        values.put(KEY_MEMBER_2_NODISEASE12MONTH, familyData[2].getNoDisease12Month());
        values.put(KEY_MEMBER_2_HASLONGTERMDISEASE, familyData[2].getHasLongTermDisease());
        values.put(KEY_MEMBER_2_NOLONGTERMDISEASE, familyData[2].getNoLongTermDisease());
        values.put(KEY_MEMBER_2_LONGTERMDISEASENAME, familyData[2].getLongTermDiseaseName());
        values.put(KEY_MEMBER_2_HASCOMMUNICABLEDISEASE, familyData[2].getHasCommunicableDisease());
        values.put(KEY_MEMBER_2_NOCOMMUNICABLEDISEASE, familyData[2].getNoCommunicableDisease());
        values.put(KEY_MEMBER_2_COMMUNICABLEDISEASENAME, familyData[2].getCommunicableDiseaseName());
        values.put(KEY_MEMBER_2_USESVACCINE, familyData[2].getUsesVaccine());
        values.put(KEY_MEMBER_2_SOCIALIDENTITY, familyData[2].getSocialIdentity());
        values.put(KEY_MEMBER_2_SOCIALSECURITY_TYPE, familyData[2].getSocialSecurity_type());
        values.put(KEY_MEMBER_2_MARITALSTATUS, familyData[2].getMaritalStatus());
        values.put(KEY_MEMBER_2_SOCIALINVOLVEMENTS, familyData[2].getSocialInvolvements());
        values.put(KEY_MEMBER_2_HASRECEIVEDTRAINING, familyData[2].getHasReceivedTraining());
        values.put(KEY_MEMBER_2_NOTRECEIVEDTRAINING, familyData[2].getNotReceivedTraining());
        values.put(KEY_MEMBER_2_TRAININGLIST, familyData[2].getTrainingList());
        values.put(KEY_MEMBER_2_ISPOLITICALINFLUENCER, familyData[2].getIsPoliticalInfluencer());
        values.put(KEY_MEMBER_2_ISNOTPOLITICALINFLUENCER, familyData[2].getIsNotPoliticalInfluencer());
        values.put(KEY_MEMBER_2_POLITICALINFLUENCERLEVEL, familyData[2].getPoliticalInfluencerLevel());
        values.put(KEY_MEMBER_2_TRAVELWORK, familyData[2].getTravelWork());
        values.put(KEY_MEMBER_2_TRAVELBUSINESS, familyData[2].getTravelBusiness());
        values.put(KEY_MEMBER_2_TRAVELEDUCATION, familyData[2].getTravelEducation());
        values.put(KEY_MEMBER_2_TRAVELOTHERS, familyData[2].getTravelOthers());

        values.put(KEY_MEMBER_3_NAME, familyData[3].getName());
        values.put(KEY_MEMBER_3_CAST, familyData[3].getCast());
        values.put(KEY_MEMBER_3_GENDER, familyData[3].getGender());
        values.put(KEY_MEMBER_3_AGE, familyData[3].getAge());
        values.put(KEY_MEMBER_3_RELATIONTOOWNER, familyData[3].getRelationToOwner());
        values.put(KEY_MEMBER_3_BIRTHPLACE, familyData[3].getBirthplace());
        values.put(KEY_MEMBER_3_HASEMAIL, familyData[3].getHasEmail());
        values.put(KEY_MEMBER_3_HASNOEMAIL, familyData[3].getHasNoEmail());
        values.put(KEY_MEMBER_3_HASLEFTHOME6MONTH, familyData[3].getHasLeftHome6Month());
        values.put(KEY_MEMBER_3_HASNOTLEFTHOME6MONTH, familyData[3].getHasNotLeftHome6Month());
        values.put(KEY_MEMBER_3_LEAVEHOME_PLACE, familyData[3].getLeaveHome_place());
        values.put(KEY_MEMBER_3_LEAVEHOME_REASON, familyData[3].getLeaveHome_reason());
        values.put(KEY_MEMBER_3_EDUCATION, familyData[3].getEducation());
        values.put(KEY_MEMBER_3_SCHOOLTYPE, familyData[3].getSchoolType());
        values.put(KEY_MEMBER_3_LEAVESCHOOL_REASON, familyData[3].getLeaveSchool_reason());
        values.put(KEY_MEMBER_3_SCHOOLLEVEL, familyData[3].getSchoolLevel());
        values.put(KEY_MEMBER_3_INCOMESOURCE, familyData[3].getIncomeSource());
        values.put(KEY_MEMBER_3_ABROAD_COUNTRY, familyData[3].getAbroad_country());
        values.put(KEY_MEMBER_3_ABROAD_MONEYTRANSFER, familyData[3].getAbroad_moneyTransfer());
        values.put(KEY_MEMBER_3_SKILLS, familyData[3].getSkills());
        values.put(KEY_MEMBER_3_HASBANKAC, familyData[3].getHasBankAC());
        values.put(KEY_MEMBER_3_HASNOBANKAC, familyData[3].getHasNoBankAC());
        values.put(KEY_MEMBER_3_HASATM, familyData[3].getHasATM());
        values.put(KEY_MEMBER_3_HASNOATM, familyData[3].getHasNoATM());
        values.put(KEY_MEMBER_3_USEONLINEBANKING, familyData[3].getUseOnlineBanking());
        values.put(KEY_MEMBER_3_NOTUSEONLINEBANKING, familyData[3].getNotUseOnlineBanking());
        values.put(KEY_MEMBER_3_REGULARDEPOSIT, familyData[3].getRegularDeposit());
        values.put(KEY_MEMBER_3_NOTREGULARDEPOSIT, familyData[3].getNotRegularDeposit());
        values.put(KEY_MEMBER_3_REGULARDEPOSIT_TO, familyData[3].getRegularDeposit_to());
        values.put(KEY_MEMBER_3_ISHEALTHY, familyData[3].getIsHealthy());
        values.put(KEY_MEMBER_3_ISDISABLED, familyData[3].getIsDisabled());
        values.put(KEY_MEMBER_3_DISABILITYTYPE, familyData[3].getDisabilityType());
        values.put(KEY_MEMBER_3_HASDISABILITYCARD, familyData[3].getHasDisabilityCard());
        values.put(KEY_MEMBER_3_HASNODISABILITYCARD, familyData[3].getHasNoDisabilityCard());
        values.put(KEY_MEMBER_3_HASDISEASE12MONTH, familyData[3].getHasDisease12Month());
        values.put(KEY_MEMBER_3_NODISEASE12MONTH, familyData[3].getNoDisease12Month());
        values.put(KEY_MEMBER_3_HASLONGTERMDISEASE, familyData[3].getHasLongTermDisease());
        values.put(KEY_MEMBER_3_NOLONGTERMDISEASE, familyData[3].getNoLongTermDisease());
        values.put(KEY_MEMBER_3_LONGTERMDISEASENAME, familyData[3].getLongTermDiseaseName());
        values.put(KEY_MEMBER_3_HASCOMMUNICABLEDISEASE, familyData[3].getHasCommunicableDisease());
        values.put(KEY_MEMBER_3_NOCOMMUNICABLEDISEASE, familyData[3].getNoCommunicableDisease());
        values.put(KEY_MEMBER_3_COMMUNICABLEDISEASENAME, familyData[3].getCommunicableDiseaseName());
        values.put(KEY_MEMBER_3_USESVACCINE, familyData[3].getUsesVaccine());
        values.put(KEY_MEMBER_3_SOCIALIDENTITY, familyData[3].getSocialIdentity());
        values.put(KEY_MEMBER_3_SOCIALSECURITY_TYPE, familyData[3].getSocialSecurity_type());
        values.put(KEY_MEMBER_3_MARITALSTATUS, familyData[3].getMaritalStatus());
        values.put(KEY_MEMBER_3_SOCIALINVOLVEMENTS, familyData[3].getSocialInvolvements());
        values.put(KEY_MEMBER_3_HASRECEIVEDTRAINING, familyData[3].getHasReceivedTraining());
        values.put(KEY_MEMBER_3_NOTRECEIVEDTRAINING, familyData[3].getNotReceivedTraining());
        values.put(KEY_MEMBER_3_TRAININGLIST, familyData[3].getTrainingList());
        values.put(KEY_MEMBER_3_ISPOLITICALINFLUENCER, familyData[3].getIsPoliticalInfluencer());
        values.put(KEY_MEMBER_3_ISNOTPOLITICALINFLUENCER, familyData[3].getIsNotPoliticalInfluencer());
        values.put(KEY_MEMBER_3_POLITICALINFLUENCERLEVEL, familyData[3].getPoliticalInfluencerLevel());
        values.put(KEY_MEMBER_3_TRAVELWORK, familyData[3].getTravelWork());
        values.put(KEY_MEMBER_3_TRAVELBUSINESS, familyData[3].getTravelBusiness());
        values.put(KEY_MEMBER_3_TRAVELEDUCATION, familyData[3].getTravelEducation());
        values.put(KEY_MEMBER_3_TRAVELOTHERS, familyData[3].getTravelOthers());

        values.put(KEY_MEMBER_4_NAME, familyData[4].getName());
        values.put(KEY_MEMBER_4_CAST, familyData[4].getCast());
        values.put(KEY_MEMBER_4_GENDER, familyData[4].getGender());
        values.put(KEY_MEMBER_4_AGE, familyData[4].getAge());
        values.put(KEY_MEMBER_4_RELATIONTOOWNER, familyData[4].getRelationToOwner());
        values.put(KEY_MEMBER_4_BIRTHPLACE, familyData[4].getBirthplace());
        values.put(KEY_MEMBER_4_HASEMAIL, familyData[4].getHasEmail());
        values.put(KEY_MEMBER_4_HASNOEMAIL, familyData[4].getHasNoEmail());
        values.put(KEY_MEMBER_4_HASLEFTHOME6MONTH, familyData[4].getHasLeftHome6Month());
        values.put(KEY_MEMBER_4_HASNOTLEFTHOME6MONTH, familyData[4].getHasNotLeftHome6Month());
        values.put(KEY_MEMBER_4_LEAVEHOME_PLACE, familyData[4].getLeaveHome_place());
        values.put(KEY_MEMBER_4_LEAVEHOME_REASON, familyData[4].getLeaveHome_reason());
        values.put(KEY_MEMBER_4_EDUCATION, familyData[4].getEducation());
        values.put(KEY_MEMBER_4_SCHOOLTYPE, familyData[4].getSchoolType());
        values.put(KEY_MEMBER_4_LEAVESCHOOL_REASON, familyData[4].getLeaveSchool_reason());
        values.put(KEY_MEMBER_4_SCHOOLLEVEL, familyData[4].getSchoolLevel());
        values.put(KEY_MEMBER_4_INCOMESOURCE, familyData[4].getIncomeSource());
        values.put(KEY_MEMBER_4_ABROAD_COUNTRY, familyData[4].getAbroad_country());
        values.put(KEY_MEMBER_4_ABROAD_MONEYTRANSFER, familyData[4].getAbroad_moneyTransfer());
        values.put(KEY_MEMBER_4_SKILLS, familyData[4].getSkills());
        values.put(KEY_MEMBER_4_HASBANKAC, familyData[4].getHasBankAC());
        values.put(KEY_MEMBER_4_HASNOBANKAC, familyData[4].getHasNoBankAC());
        values.put(KEY_MEMBER_4_HASATM, familyData[4].getHasATM());
        values.put(KEY_MEMBER_4_HASNOATM, familyData[4].getHasNoATM());
        values.put(KEY_MEMBER_4_USEONLINEBANKING, familyData[4].getUseOnlineBanking());
        values.put(KEY_MEMBER_4_NOTUSEONLINEBANKING, familyData[4].getNotUseOnlineBanking());
        values.put(KEY_MEMBER_4_REGULARDEPOSIT, familyData[4].getRegularDeposit());
        values.put(KEY_MEMBER_4_NOTREGULARDEPOSIT, familyData[4].getNotRegularDeposit());
        values.put(KEY_MEMBER_4_REGULARDEPOSIT_TO, familyData[4].getRegularDeposit_to());
        values.put(KEY_MEMBER_4_ISHEALTHY, familyData[4].getIsHealthy());
        values.put(KEY_MEMBER_4_ISDISABLED, familyData[4].getIsDisabled());
        values.put(KEY_MEMBER_4_DISABILITYTYPE, familyData[4].getDisabilityType());
        values.put(KEY_MEMBER_4_HASDISABILITYCARD, familyData[4].getHasDisabilityCard());
        values.put(KEY_MEMBER_4_HASNODISABILITYCARD, familyData[4].getHasNoDisabilityCard());
        values.put(KEY_MEMBER_4_HASDISEASE12MONTH, familyData[4].getHasDisease12Month());
        values.put(KEY_MEMBER_4_NODISEASE12MONTH, familyData[4].getNoDisease12Month());
        values.put(KEY_MEMBER_4_HASLONGTERMDISEASE, familyData[4].getHasLongTermDisease());
        values.put(KEY_MEMBER_4_NOLONGTERMDISEASE, familyData[4].getNoLongTermDisease());
        values.put(KEY_MEMBER_4_LONGTERMDISEASENAME, familyData[4].getLongTermDiseaseName());
        values.put(KEY_MEMBER_4_HASCOMMUNICABLEDISEASE, familyData[4].getHasCommunicableDisease());
        values.put(KEY_MEMBER_4_NOCOMMUNICABLEDISEASE, familyData[4].getNoCommunicableDisease());
        values.put(KEY_MEMBER_4_COMMUNICABLEDISEASENAME, familyData[4].getCommunicableDiseaseName());
        values.put(KEY_MEMBER_4_USESVACCINE, familyData[4].getUsesVaccine());
        values.put(KEY_MEMBER_4_SOCIALIDENTITY, familyData[4].getSocialIdentity());
        values.put(KEY_MEMBER_4_SOCIALSECURITY_TYPE, familyData[4].getSocialSecurity_type());
        values.put(KEY_MEMBER_4_MARITALSTATUS, familyData[4].getMaritalStatus());
        values.put(KEY_MEMBER_4_SOCIALINVOLVEMENTS, familyData[4].getSocialInvolvements());
        values.put(KEY_MEMBER_4_HASRECEIVEDTRAINING, familyData[4].getHasReceivedTraining());
        values.put(KEY_MEMBER_4_NOTRECEIVEDTRAINING, familyData[4].getNotReceivedTraining());
        values.put(KEY_MEMBER_4_TRAININGLIST, familyData[4].getTrainingList());
        values.put(KEY_MEMBER_4_ISPOLITICALINFLUENCER, familyData[4].getIsPoliticalInfluencer());
        values.put(KEY_MEMBER_4_ISNOTPOLITICALINFLUENCER, familyData[4].getIsNotPoliticalInfluencer());
        values.put(KEY_MEMBER_4_POLITICALINFLUENCERLEVEL, familyData[4].getPoliticalInfluencerLevel());
        values.put(KEY_MEMBER_4_TRAVELWORK, familyData[4].getTravelWork());
        values.put(KEY_MEMBER_4_TRAVELBUSINESS, familyData[4].getTravelBusiness());
        values.put(KEY_MEMBER_4_TRAVELEDUCATION, familyData[4].getTravelEducation());
        values.put(KEY_MEMBER_4_TRAVELOTHERS, familyData[4].getTravelOthers());

        values.put(KEY_MEMBER_5_NAME, familyData[5].getName());
        values.put(KEY_MEMBER_5_CAST, familyData[5].getCast());
        values.put(KEY_MEMBER_5_GENDER, familyData[5].getGender());
        values.put(KEY_MEMBER_5_AGE, familyData[5].getAge());
        values.put(KEY_MEMBER_5_RELATIONTOOWNER, familyData[5].getRelationToOwner());
        values.put(KEY_MEMBER_5_BIRTHPLACE, familyData[5].getBirthplace());
        values.put(KEY_MEMBER_5_HASEMAIL, familyData[5].getHasEmail());
        values.put(KEY_MEMBER_5_HASNOEMAIL, familyData[5].getHasNoEmail());
        values.put(KEY_MEMBER_5_HASLEFTHOME6MONTH, familyData[5].getHasLeftHome6Month());
        values.put(KEY_MEMBER_5_HASNOTLEFTHOME6MONTH, familyData[5].getHasNotLeftHome6Month());
        values.put(KEY_MEMBER_5_LEAVEHOME_PLACE, familyData[5].getLeaveHome_place());
        values.put(KEY_MEMBER_5_LEAVEHOME_REASON, familyData[5].getLeaveHome_reason());
        values.put(KEY_MEMBER_5_EDUCATION, familyData[5].getEducation());
        values.put(KEY_MEMBER_5_SCHOOLTYPE, familyData[5].getSchoolType());
        values.put(KEY_MEMBER_5_LEAVESCHOOL_REASON, familyData[5].getLeaveSchool_reason());
        values.put(KEY_MEMBER_5_SCHOOLLEVEL, familyData[5].getSchoolLevel());
        values.put(KEY_MEMBER_5_INCOMESOURCE, familyData[5].getIncomeSource());
        values.put(KEY_MEMBER_5_ABROAD_COUNTRY, familyData[5].getAbroad_country());
        values.put(KEY_MEMBER_5_ABROAD_MONEYTRANSFER, familyData[5].getAbroad_moneyTransfer());
        values.put(KEY_MEMBER_5_SKILLS, familyData[5].getSkills());
        values.put(KEY_MEMBER_5_HASBANKAC, familyData[5].getHasBankAC());
        values.put(KEY_MEMBER_5_HASNOBANKAC, familyData[5].getHasNoBankAC());
        values.put(KEY_MEMBER_5_HASATM, familyData[5].getHasATM());
        values.put(KEY_MEMBER_5_HASNOATM, familyData[5].getHasNoATM());
        values.put(KEY_MEMBER_5_USEONLINEBANKING, familyData[5].getUseOnlineBanking());
        values.put(KEY_MEMBER_5_NOTUSEONLINEBANKING, familyData[5].getNotUseOnlineBanking());
        values.put(KEY_MEMBER_5_REGULARDEPOSIT, familyData[5].getRegularDeposit());
        values.put(KEY_MEMBER_5_NOTREGULARDEPOSIT, familyData[5].getNotRegularDeposit());
        values.put(KEY_MEMBER_5_REGULARDEPOSIT_TO, familyData[5].getRegularDeposit_to());
        values.put(KEY_MEMBER_5_ISHEALTHY, familyData[5].getIsHealthy());
        values.put(KEY_MEMBER_5_ISDISABLED, familyData[5].getIsDisabled());
        values.put(KEY_MEMBER_5_DISABILITYTYPE, familyData[5].getDisabilityType());
        values.put(KEY_MEMBER_5_HASDISABILITYCARD, familyData[5].getHasDisabilityCard());
        values.put(KEY_MEMBER_5_HASNODISABILITYCARD, familyData[5].getHasNoDisabilityCard());
        values.put(KEY_MEMBER_5_HASDISEASE12MONTH, familyData[5].getHasDisease12Month());
        values.put(KEY_MEMBER_5_NODISEASE12MONTH, familyData[5].getNoDisease12Month());
        values.put(KEY_MEMBER_5_HASLONGTERMDISEASE, familyData[5].getHasLongTermDisease());
        values.put(KEY_MEMBER_5_NOLONGTERMDISEASE, familyData[5].getNoLongTermDisease());
        values.put(KEY_MEMBER_5_LONGTERMDISEASENAME, familyData[5].getLongTermDiseaseName());
        values.put(KEY_MEMBER_5_HASCOMMUNICABLEDISEASE, familyData[5].getHasCommunicableDisease());
        values.put(KEY_MEMBER_5_NOCOMMUNICABLEDISEASE, familyData[5].getNoCommunicableDisease());
        values.put(KEY_MEMBER_5_COMMUNICABLEDISEASENAME, familyData[5].getCommunicableDiseaseName());
        values.put(KEY_MEMBER_5_USESVACCINE, familyData[5].getUsesVaccine());
        values.put(KEY_MEMBER_5_SOCIALIDENTITY, familyData[5].getSocialIdentity());
        values.put(KEY_MEMBER_5_SOCIALSECURITY_TYPE, familyData[5].getSocialSecurity_type());
        values.put(KEY_MEMBER_5_MARITALSTATUS, familyData[5].getMaritalStatus());
        values.put(KEY_MEMBER_5_SOCIALINVOLVEMENTS, familyData[5].getSocialInvolvements());
        values.put(KEY_MEMBER_5_HASRECEIVEDTRAINING, familyData[5].getHasReceivedTraining());
        values.put(KEY_MEMBER_5_NOTRECEIVEDTRAINING, familyData[5].getNotReceivedTraining());
        values.put(KEY_MEMBER_5_TRAININGLIST, familyData[5].getTrainingList());
        values.put(KEY_MEMBER_5_ISPOLITICALINFLUENCER, familyData[5].getIsPoliticalInfluencer());
        values.put(KEY_MEMBER_5_ISNOTPOLITICALINFLUENCER, familyData[5].getIsNotPoliticalInfluencer());
        values.put(KEY_MEMBER_5_POLITICALINFLUENCERLEVEL, familyData[5].getPoliticalInfluencerLevel());
        values.put(KEY_MEMBER_5_TRAVELWORK, familyData[5].getTravelWork());
        values.put(KEY_MEMBER_5_TRAVELBUSINESS, familyData[5].getTravelBusiness());
        values.put(KEY_MEMBER_5_TRAVELEDUCATION, familyData[5].getTravelEducation());
        values.put(KEY_MEMBER_5_TRAVELOTHERS, familyData[5].getTravelOthers());

        values.put(KEY_MEMBER_6_NAME, familyData[6].getName());
        values.put(KEY_MEMBER_6_CAST, familyData[6].getCast());
        values.put(KEY_MEMBER_6_GENDER, familyData[6].getGender());
        values.put(KEY_MEMBER_6_AGE, familyData[6].getAge());
        values.put(KEY_MEMBER_6_RELATIONTOOWNER, familyData[6].getRelationToOwner());
        values.put(KEY_MEMBER_6_BIRTHPLACE, familyData[6].getBirthplace());
        values.put(KEY_MEMBER_6_HASEMAIL, familyData[6].getHasEmail());
        values.put(KEY_MEMBER_6_HASNOEMAIL, familyData[6].getHasNoEmail());
        values.put(KEY_MEMBER_6_HASLEFTHOME6MONTH, familyData[6].getHasLeftHome6Month());
        values.put(KEY_MEMBER_6_HASNOTLEFTHOME6MONTH, familyData[6].getHasNotLeftHome6Month());
        values.put(KEY_MEMBER_6_LEAVEHOME_PLACE, familyData[6].getLeaveHome_place());
        values.put(KEY_MEMBER_6_LEAVEHOME_REASON, familyData[6].getLeaveHome_reason());
        values.put(KEY_MEMBER_6_EDUCATION, familyData[6].getEducation());
        values.put(KEY_MEMBER_6_SCHOOLTYPE, familyData[6].getSchoolType());
        values.put(KEY_MEMBER_6_LEAVESCHOOL_REASON, familyData[6].getLeaveSchool_reason());
        values.put(KEY_MEMBER_6_SCHOOLLEVEL, familyData[6].getSchoolLevel());
        values.put(KEY_MEMBER_6_INCOMESOURCE, familyData[6].getIncomeSource());
        values.put(KEY_MEMBER_6_ABROAD_COUNTRY, familyData[6].getAbroad_country());
        values.put(KEY_MEMBER_6_ABROAD_MONEYTRANSFER, familyData[6].getAbroad_moneyTransfer());
        values.put(KEY_MEMBER_6_SKILLS, familyData[6].getSkills());
        values.put(KEY_MEMBER_6_HASBANKAC, familyData[6].getHasBankAC());
        values.put(KEY_MEMBER_6_HASNOBANKAC, familyData[6].getHasNoBankAC());
        values.put(KEY_MEMBER_6_HASATM, familyData[6].getHasATM());
        values.put(KEY_MEMBER_6_HASNOATM, familyData[6].getHasNoATM());
        values.put(KEY_MEMBER_6_USEONLINEBANKING, familyData[6].getUseOnlineBanking());
        values.put(KEY_MEMBER_6_NOTUSEONLINEBANKING, familyData[6].getNotUseOnlineBanking());
        values.put(KEY_MEMBER_6_REGULARDEPOSIT, familyData[6].getRegularDeposit());
        values.put(KEY_MEMBER_6_NOTREGULARDEPOSIT, familyData[6].getNotRegularDeposit());
        values.put(KEY_MEMBER_6_REGULARDEPOSIT_TO, familyData[6].getRegularDeposit_to());
        values.put(KEY_MEMBER_6_ISHEALTHY, familyData[6].getIsHealthy());
        values.put(KEY_MEMBER_6_ISDISABLED, familyData[6].getIsDisabled());
        values.put(KEY_MEMBER_6_DISABILITYTYPE, familyData[6].getDisabilityType());
        values.put(KEY_MEMBER_6_HASDISABILITYCARD, familyData[6].getHasDisabilityCard());
        values.put(KEY_MEMBER_6_HASNODISABILITYCARD, familyData[6].getHasNoDisabilityCard());
        values.put(KEY_MEMBER_6_HASDISEASE12MONTH, familyData[6].getHasDisease12Month());
        values.put(KEY_MEMBER_6_NODISEASE12MONTH, familyData[6].getNoDisease12Month());
        values.put(KEY_MEMBER_6_HASLONGTERMDISEASE, familyData[6].getHasLongTermDisease());
        values.put(KEY_MEMBER_6_NOLONGTERMDISEASE, familyData[6].getNoLongTermDisease());
        values.put(KEY_MEMBER_6_LONGTERMDISEASENAME, familyData[6].getLongTermDiseaseName());
        values.put(KEY_MEMBER_6_HASCOMMUNICABLEDISEASE, familyData[6].getHasCommunicableDisease());
        values.put(KEY_MEMBER_6_NOCOMMUNICABLEDISEASE, familyData[6].getNoCommunicableDisease());
        values.put(KEY_MEMBER_6_COMMUNICABLEDISEASENAME, familyData[6].getCommunicableDiseaseName());
        values.put(KEY_MEMBER_6_USESVACCINE, familyData[6].getUsesVaccine());
        values.put(KEY_MEMBER_6_SOCIALIDENTITY, familyData[6].getSocialIdentity());
        values.put(KEY_MEMBER_6_SOCIALSECURITY_TYPE, familyData[6].getSocialSecurity_type());
        values.put(KEY_MEMBER_6_MARITALSTATUS, familyData[6].getMaritalStatus());
        values.put(KEY_MEMBER_6_SOCIALINVOLVEMENTS, familyData[6].getSocialInvolvements());
        values.put(KEY_MEMBER_6_HASRECEIVEDTRAINING, familyData[6].getHasReceivedTraining());
        values.put(KEY_MEMBER_6_NOTRECEIVEDTRAINING, familyData[6].getNotReceivedTraining());
        values.put(KEY_MEMBER_6_TRAININGLIST, familyData[6].getTrainingList());
        values.put(KEY_MEMBER_6_ISPOLITICALINFLUENCER, familyData[6].getIsPoliticalInfluencer());
        values.put(KEY_MEMBER_6_ISNOTPOLITICALINFLUENCER, familyData[6].getIsNotPoliticalInfluencer());
        values.put(KEY_MEMBER_6_POLITICALINFLUENCERLEVEL, familyData[6].getPoliticalInfluencerLevel());
        values.put(KEY_MEMBER_6_TRAVELWORK, familyData[6].getTravelWork());
        values.put(KEY_MEMBER_6_TRAVELBUSINESS, familyData[6].getTravelBusiness());
        values.put(KEY_MEMBER_6_TRAVELEDUCATION, familyData[6].getTravelEducation());
        values.put(KEY_MEMBER_6_TRAVELOTHERS, familyData[6].getTravelOthers());

        values.put(KEY_MEMBER_7_NAME, familyData[7].getName());
        values.put(KEY_MEMBER_7_CAST, familyData[7].getCast());
        values.put(KEY_MEMBER_7_GENDER, familyData[7].getGender());
        values.put(KEY_MEMBER_7_AGE, familyData[7].getAge());
        values.put(KEY_MEMBER_7_RELATIONTOOWNER, familyData[7].getRelationToOwner());
        values.put(KEY_MEMBER_7_BIRTHPLACE, familyData[7].getBirthplace());
        values.put(KEY_MEMBER_7_HASEMAIL, familyData[7].getHasEmail());
        values.put(KEY_MEMBER_7_HASNOEMAIL, familyData[7].getHasNoEmail());
        values.put(KEY_MEMBER_7_HASLEFTHOME6MONTH, familyData[7].getHasLeftHome6Month());
        values.put(KEY_MEMBER_7_HASNOTLEFTHOME6MONTH, familyData[7].getHasNotLeftHome6Month());
        values.put(KEY_MEMBER_7_LEAVEHOME_PLACE, familyData[7].getLeaveHome_place());
        values.put(KEY_MEMBER_7_LEAVEHOME_REASON, familyData[7].getLeaveHome_reason());
        values.put(KEY_MEMBER_7_EDUCATION, familyData[7].getEducation());
        values.put(KEY_MEMBER_7_SCHOOLTYPE, familyData[7].getSchoolType());
        values.put(KEY_MEMBER_7_LEAVESCHOOL_REASON, familyData[7].getLeaveSchool_reason());
        values.put(KEY_MEMBER_7_SCHOOLLEVEL, familyData[7].getSchoolLevel());
        values.put(KEY_MEMBER_7_INCOMESOURCE, familyData[7].getIncomeSource());
        values.put(KEY_MEMBER_7_ABROAD_COUNTRY, familyData[7].getAbroad_country());
        values.put(KEY_MEMBER_7_ABROAD_MONEYTRANSFER, familyData[7].getAbroad_moneyTransfer());
        values.put(KEY_MEMBER_7_SKILLS, familyData[7].getSkills());
        values.put(KEY_MEMBER_7_HASBANKAC, familyData[7].getHasBankAC());
        values.put(KEY_MEMBER_7_HASNOBANKAC, familyData[7].getHasNoBankAC());
        values.put(KEY_MEMBER_7_HASATM, familyData[7].getHasATM());
        values.put(KEY_MEMBER_7_HASNOATM, familyData[7].getHasNoATM());
        values.put(KEY_MEMBER_7_USEONLINEBANKING, familyData[7].getUseOnlineBanking());
        values.put(KEY_MEMBER_7_NOTUSEONLINEBANKING, familyData[7].getNotUseOnlineBanking());
        values.put(KEY_MEMBER_7_REGULARDEPOSIT, familyData[7].getRegularDeposit());
        values.put(KEY_MEMBER_7_NOTREGULARDEPOSIT, familyData[7].getNotRegularDeposit());
        values.put(KEY_MEMBER_7_REGULARDEPOSIT_TO, familyData[7].getRegularDeposit_to());
        values.put(KEY_MEMBER_7_ISHEALTHY, familyData[7].getIsHealthy());
        values.put(KEY_MEMBER_7_ISDISABLED, familyData[7].getIsDisabled());
        values.put(KEY_MEMBER_7_DISABILITYTYPE, familyData[7].getDisabilityType());
        values.put(KEY_MEMBER_7_HASDISABILITYCARD, familyData[7].getHasDisabilityCard());
        values.put(KEY_MEMBER_7_HASNODISABILITYCARD, familyData[7].getHasNoDisabilityCard());
        values.put(KEY_MEMBER_7_HASDISEASE12MONTH, familyData[7].getHasDisease12Month());
        values.put(KEY_MEMBER_7_NODISEASE12MONTH, familyData[7].getNoDisease12Month());
        values.put(KEY_MEMBER_7_HASLONGTERMDISEASE, familyData[7].getHasLongTermDisease());
        values.put(KEY_MEMBER_7_NOLONGTERMDISEASE, familyData[7].getNoLongTermDisease());
        values.put(KEY_MEMBER_7_LONGTERMDISEASENAME, familyData[7].getLongTermDiseaseName());
        values.put(KEY_MEMBER_7_HASCOMMUNICABLEDISEASE, familyData[7].getHasCommunicableDisease());
        values.put(KEY_MEMBER_7_NOCOMMUNICABLEDISEASE, familyData[7].getNoCommunicableDisease());
        values.put(KEY_MEMBER_7_COMMUNICABLEDISEASENAME, familyData[7].getCommunicableDiseaseName());
        values.put(KEY_MEMBER_7_USESVACCINE, familyData[7].getUsesVaccine());
        values.put(KEY_MEMBER_7_SOCIALIDENTITY, familyData[7].getSocialIdentity());
        values.put(KEY_MEMBER_7_SOCIALSECURITY_TYPE, familyData[7].getSocialSecurity_type());
        values.put(KEY_MEMBER_7_MARITALSTATUS, familyData[7].getMaritalStatus());
        values.put(KEY_MEMBER_7_SOCIALINVOLVEMENTS, familyData[7].getSocialInvolvements());
        values.put(KEY_MEMBER_7_HASRECEIVEDTRAINING, familyData[7].getHasReceivedTraining());
        values.put(KEY_MEMBER_7_NOTRECEIVEDTRAINING, familyData[7].getNotReceivedTraining());
        values.put(KEY_MEMBER_7_TRAININGLIST, familyData[7].getTrainingList());
        values.put(KEY_MEMBER_7_ISPOLITICALINFLUENCER, familyData[7].getIsPoliticalInfluencer());
        values.put(KEY_MEMBER_7_ISNOTPOLITICALINFLUENCER, familyData[7].getIsNotPoliticalInfluencer());
        values.put(KEY_MEMBER_7_POLITICALINFLUENCERLEVEL, familyData[7].getPoliticalInfluencerLevel());
        values.put(KEY_MEMBER_7_TRAVELWORK, familyData[7].getTravelWork());
        values.put(KEY_MEMBER_7_TRAVELBUSINESS, familyData[7].getTravelBusiness());
        values.put(KEY_MEMBER_7_TRAVELEDUCATION, familyData[7].getTravelEducation());
        values.put(KEY_MEMBER_7_TRAVELOTHERS, familyData[7].getTravelOthers());

        values.put(KEY_MEMBER_8_NAME, familyData[8].getName());
        values.put(KEY_MEMBER_8_CAST, familyData[8].getCast());
        values.put(KEY_MEMBER_8_GENDER, familyData[8].getGender());
        values.put(KEY_MEMBER_8_AGE, familyData[8].getAge());
        values.put(KEY_MEMBER_8_RELATIONTOOWNER, familyData[8].getRelationToOwner());
        values.put(KEY_MEMBER_8_BIRTHPLACE, familyData[8].getBirthplace());
        values.put(KEY_MEMBER_8_HASEMAIL, familyData[8].getHasEmail());
        values.put(KEY_MEMBER_8_HASNOEMAIL, familyData[8].getHasNoEmail());
        values.put(KEY_MEMBER_8_HASLEFTHOME6MONTH, familyData[8].getHasLeftHome6Month());
        values.put(KEY_MEMBER_8_HASNOTLEFTHOME6MONTH, familyData[8].getHasNotLeftHome6Month());
        values.put(KEY_MEMBER_8_LEAVEHOME_PLACE, familyData[8].getLeaveHome_place());
        values.put(KEY_MEMBER_8_LEAVEHOME_REASON, familyData[8].getLeaveHome_reason());
        values.put(KEY_MEMBER_8_EDUCATION, familyData[8].getEducation());
        values.put(KEY_MEMBER_8_SCHOOLTYPE, familyData[8].getSchoolType());
        values.put(KEY_MEMBER_8_LEAVESCHOOL_REASON, familyData[8].getLeaveSchool_reason());
        values.put(KEY_MEMBER_8_SCHOOLLEVEL, familyData[8].getSchoolLevel());
        values.put(KEY_MEMBER_8_INCOMESOURCE, familyData[8].getIncomeSource());
        values.put(KEY_MEMBER_8_ABROAD_COUNTRY, familyData[8].getAbroad_country());
        values.put(KEY_MEMBER_8_ABROAD_MONEYTRANSFER, familyData[8].getAbroad_moneyTransfer());
        values.put(KEY_MEMBER_8_SKILLS, familyData[8].getSkills());
        values.put(KEY_MEMBER_8_HASBANKAC, familyData[8].getHasBankAC());
        values.put(KEY_MEMBER_8_HASNOBANKAC, familyData[8].getHasNoBankAC());
        values.put(KEY_MEMBER_8_HASATM, familyData[8].getHasATM());
        values.put(KEY_MEMBER_8_HASNOATM, familyData[8].getHasNoATM());
        values.put(KEY_MEMBER_8_USEONLINEBANKING, familyData[8].getUseOnlineBanking());
        values.put(KEY_MEMBER_8_NOTUSEONLINEBANKING, familyData[8].getNotUseOnlineBanking());
        values.put(KEY_MEMBER_8_REGULARDEPOSIT, familyData[8].getRegularDeposit());
        values.put(KEY_MEMBER_8_NOTREGULARDEPOSIT, familyData[8].getNotRegularDeposit());
        values.put(KEY_MEMBER_8_REGULARDEPOSIT_TO, familyData[8].getRegularDeposit_to());
        values.put(KEY_MEMBER_8_ISHEALTHY, familyData[8].getIsHealthy());
        values.put(KEY_MEMBER_8_ISDISABLED, familyData[8].getIsDisabled());
        values.put(KEY_MEMBER_8_DISABILITYTYPE, familyData[8].getDisabilityType());
        values.put(KEY_MEMBER_8_HASDISABILITYCARD, familyData[8].getHasDisabilityCard());
        values.put(KEY_MEMBER_8_HASNODISABILITYCARD, familyData[8].getHasNoDisabilityCard());
        values.put(KEY_MEMBER_8_HASDISEASE12MONTH, familyData[8].getHasDisease12Month());
        values.put(KEY_MEMBER_8_NODISEASE12MONTH, familyData[8].getNoDisease12Month());
        values.put(KEY_MEMBER_8_HASLONGTERMDISEASE, familyData[8].getHasLongTermDisease());
        values.put(KEY_MEMBER_8_NOLONGTERMDISEASE, familyData[8].getNoLongTermDisease());
        values.put(KEY_MEMBER_8_LONGTERMDISEASENAME, familyData[8].getLongTermDiseaseName());
        values.put(KEY_MEMBER_8_HASCOMMUNICABLEDISEASE, familyData[8].getHasCommunicableDisease());
        values.put(KEY_MEMBER_8_NOCOMMUNICABLEDISEASE, familyData[8].getNoCommunicableDisease());
        values.put(KEY_MEMBER_8_COMMUNICABLEDISEASENAME, familyData[8].getCommunicableDiseaseName());
        values.put(KEY_MEMBER_8_USESVACCINE, familyData[8].getUsesVaccine());
        values.put(KEY_MEMBER_8_SOCIALIDENTITY, familyData[8].getSocialIdentity());
        values.put(KEY_MEMBER_8_SOCIALSECURITY_TYPE, familyData[8].getSocialSecurity_type());
        values.put(KEY_MEMBER_8_MARITALSTATUS, familyData[8].getMaritalStatus());
        values.put(KEY_MEMBER_8_SOCIALINVOLVEMENTS, familyData[8].getSocialInvolvements());
        values.put(KEY_MEMBER_8_HASRECEIVEDTRAINING, familyData[8].getHasReceivedTraining());
        values.put(KEY_MEMBER_8_NOTRECEIVEDTRAINING, familyData[8].getNotReceivedTraining());
        values.put(KEY_MEMBER_8_TRAININGLIST, familyData[8].getTrainingList());
        values.put(KEY_MEMBER_8_ISPOLITICALINFLUENCER, familyData[8].getIsPoliticalInfluencer());
        values.put(KEY_MEMBER_8_ISNOTPOLITICALINFLUENCER, familyData[8].getIsNotPoliticalInfluencer());
        values.put(KEY_MEMBER_8_POLITICALINFLUENCERLEVEL, familyData[8].getPoliticalInfluencerLevel());
        values.put(KEY_MEMBER_8_TRAVELWORK, familyData[8].getTravelWork());
        values.put(KEY_MEMBER_8_TRAVELBUSINESS, familyData[8].getTravelBusiness());
        values.put(KEY_MEMBER_8_TRAVELEDUCATION, familyData[8].getTravelEducation());
        values.put(KEY_MEMBER_8_TRAVELOTHERS, familyData[8].getTravelOthers());

        values.put(KEY_MEMBER_9_NAME, familyData[9].getName());
        values.put(KEY_MEMBER_9_CAST, familyData[9].getCast());
        values.put(KEY_MEMBER_9_GENDER, familyData[9].getGender());
        values.put(KEY_MEMBER_9_AGE, familyData[9].getAge());
        values.put(KEY_MEMBER_9_RELATIONTOOWNER, familyData[9].getRelationToOwner());
        values.put(KEY_MEMBER_9_BIRTHPLACE, familyData[9].getBirthplace());
        values.put(KEY_MEMBER_9_HASEMAIL, familyData[9].getHasEmail());
        values.put(KEY_MEMBER_9_HASNOEMAIL, familyData[9].getHasNoEmail());
        values.put(KEY_MEMBER_9_HASLEFTHOME6MONTH, familyData[9].getHasLeftHome6Month());
        values.put(KEY_MEMBER_9_HASNOTLEFTHOME6MONTH, familyData[9].getHasNotLeftHome6Month());
        values.put(KEY_MEMBER_9_LEAVEHOME_PLACE, familyData[9].getLeaveHome_place());
        values.put(KEY_MEMBER_9_LEAVEHOME_REASON, familyData[9].getLeaveHome_reason());
        values.put(KEY_MEMBER_9_EDUCATION, familyData[9].getEducation());
        values.put(KEY_MEMBER_9_SCHOOLTYPE, familyData[9].getSchoolType());
        values.put(KEY_MEMBER_9_LEAVESCHOOL_REASON, familyData[9].getLeaveSchool_reason());
        values.put(KEY_MEMBER_9_SCHOOLLEVEL, familyData[9].getSchoolLevel());
        values.put(KEY_MEMBER_9_INCOMESOURCE, familyData[9].getIncomeSource());
        values.put(KEY_MEMBER_9_ABROAD_COUNTRY, familyData[9].getAbroad_country());
        values.put(KEY_MEMBER_9_ABROAD_MONEYTRANSFER, familyData[9].getAbroad_moneyTransfer());
        values.put(KEY_MEMBER_9_SKILLS, familyData[9].getSkills());
        values.put(KEY_MEMBER_9_HASBANKAC, familyData[9].getHasBankAC());
        values.put(KEY_MEMBER_9_HASNOBANKAC, familyData[9].getHasNoBankAC());
        values.put(KEY_MEMBER_9_HASATM, familyData[9].getHasATM());
        values.put(KEY_MEMBER_9_HASNOATM, familyData[9].getHasNoATM());
        values.put(KEY_MEMBER_9_USEONLINEBANKING, familyData[9].getUseOnlineBanking());
        values.put(KEY_MEMBER_9_NOTUSEONLINEBANKING, familyData[9].getNotUseOnlineBanking());
        values.put(KEY_MEMBER_9_REGULARDEPOSIT, familyData[9].getRegularDeposit());
        values.put(KEY_MEMBER_9_NOTREGULARDEPOSIT, familyData[9].getNotRegularDeposit());
        values.put(KEY_MEMBER_9_REGULARDEPOSIT_TO, familyData[9].getRegularDeposit_to());
        values.put(KEY_MEMBER_9_ISHEALTHY, familyData[9].getIsHealthy());
        values.put(KEY_MEMBER_9_ISDISABLED, familyData[9].getIsDisabled());
        values.put(KEY_MEMBER_9_DISABILITYTYPE, familyData[9].getDisabilityType());
        values.put(KEY_MEMBER_9_HASDISABILITYCARD, familyData[9].getHasDisabilityCard());
        values.put(KEY_MEMBER_9_HASNODISABILITYCARD, familyData[9].getHasNoDisabilityCard());
        values.put(KEY_MEMBER_9_HASDISEASE12MONTH, familyData[9].getHasDisease12Month());
        values.put(KEY_MEMBER_9_NODISEASE12MONTH, familyData[9].getNoDisease12Month());
        values.put(KEY_MEMBER_9_HASLONGTERMDISEASE, familyData[9].getHasLongTermDisease());
        values.put(KEY_MEMBER_9_NOLONGTERMDISEASE, familyData[9].getNoLongTermDisease());
        values.put(KEY_MEMBER_9_LONGTERMDISEASENAME, familyData[9].getLongTermDiseaseName());
        values.put(KEY_MEMBER_9_HASCOMMUNICABLEDISEASE, familyData[9].getHasCommunicableDisease());
        values.put(KEY_MEMBER_9_NOCOMMUNICABLEDISEASE, familyData[9].getNoCommunicableDisease());
        values.put(KEY_MEMBER_9_COMMUNICABLEDISEASENAME, familyData[9].getCommunicableDiseaseName());
        values.put(KEY_MEMBER_9_USESVACCINE, familyData[9].getUsesVaccine());
        values.put(KEY_MEMBER_9_SOCIALIDENTITY, familyData[9].getSocialIdentity());
        values.put(KEY_MEMBER_9_SOCIALSECURITY_TYPE, familyData[9].getSocialSecurity_type());
        values.put(KEY_MEMBER_9_MARITALSTATUS, familyData[9].getMaritalStatus());
        values.put(KEY_MEMBER_9_SOCIALINVOLVEMENTS, familyData[9].getSocialInvolvements());
        values.put(KEY_MEMBER_9_HASRECEIVEDTRAINING, familyData[9].getHasReceivedTraining());
        values.put(KEY_MEMBER_9_NOTRECEIVEDTRAINING, familyData[9].getNotReceivedTraining());
        values.put(KEY_MEMBER_9_TRAININGLIST, familyData[9].getTrainingList());
        values.put(KEY_MEMBER_9_ISPOLITICALINFLUENCER, familyData[9].getIsPoliticalInfluencer());
        values.put(KEY_MEMBER_9_ISNOTPOLITICALINFLUENCER, familyData[9].getIsNotPoliticalInfluencer());
        values.put(KEY_MEMBER_9_POLITICALINFLUENCERLEVEL, familyData[9].getPoliticalInfluencerLevel());
        values.put(KEY_MEMBER_9_TRAVELWORK, familyData[9].getTravelWork());
        values.put(KEY_MEMBER_9_TRAVELBUSINESS, familyData[9].getTravelBusiness());
        values.put(KEY_MEMBER_9_TRAVELEDUCATION, familyData[9].getTravelEducation());
        values.put(KEY_MEMBER_9_TRAVELOTHERS, familyData[9].getTravelOthers());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public int updateDetail(Details details) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_LAT, details.getLatitude());
        values.put(KEY_LNG, details.getLongitude());
        values.put(KEY_ALT, details.getAltitude());
        values.put(KEY_PRADESH, details.getPradesh());
        values.put(KEY_JILLA, details.getJilla());
        values.put(KEY_NAGARPALIKA, details.getNagarpalika());
        values.put(KEY_WARD, details.getWard());
        values.put(KEY_BASTI, details.getBasti());
        values.put(KEY_TOLENAME, details.getTole());
        values.put(KEY_SADAKNAME, details.getSadak());
        values.put(KEY_JATI, details.getJati());
        values.put(KEY_VASA, details.getVasa());
        values.put(KEY_DHARMA, details.getDharma());

        return db.update(TABLE_NAME, values, KEY_ID + "=?", new String[]{String.valueOf(details.getId())});


    }

    public void deleteDetail(Details details) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, KEY_ID + " =?", new String[]{String.valueOf(details.getId())});

        db.close();


    }

    public Details getDetail(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{
                        KEY_ID,
                        KEY_LAT,
                        KEY_LNG,
                        KEY_ALT,
                        KEY_PRADESH,
                        KEY_JILLA,
                        KEY_NAGARPALIKA,
                        KEY_WARD,
                        KEY_BASTI,
                        KEY_TOLENAME,
                        KEY_SADAKNAME,
                        KEY_JATI,
                        KEY_VASA,
                        KEY_DHARMA,
                        KEY_GHARDHANINAME,
                        KEY_GHARDHANIPHONE,
                        KEY_GHARDHANISEX

                }, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        return new Details(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7),
                cursor.getString(8),
                cursor.getString(9),
                cursor.getString(10),
                cursor.getString(11), //jati
                cursor.getString(12),   //vasa
                cursor.getString(13),  //dharma
                cursor.getString(14), //ghardhaniname
                cursor.getString(15), //ghardhanisex
                cursor.getString(16) //ghardhaniphone
        );
    }

    public List<Details> getAllDetails() {

        List<Details> lstDetail = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Details details = new Details();
                details.setId(cursor.getInt(0));
                details.setLatitude(cursor.getString(1));
                details.setLongitude(cursor.getString(2));
                details.setAltitude(cursor.getString(3));
                details.setPradesh(cursor.getString(4));
                details.setJilla(cursor.getString(5));
                details.setNagarpalika(cursor.getString(6));
                details.setWard(cursor.getString(7));
                details.setBasti(cursor.getString(8));
                details.setTole(cursor.getString(9));
                details.setSadak(cursor.getString(10));
                details.setJati(cursor.getString(11));
                details.setVasa(cursor.getString(12));
                details.setDharma(cursor.getString(13));
                details.setGhardhaniname(cursor.getString(14));
                details.setGhardhanisex(cursor.getString(15));
                details.setGhardhaniphone(cursor.getString(16));


                lstDetail.add(details);
            }
            while (cursor.moveToNext());

        }

        return lstDetail;

    }

    public void exportToCSV() {
        String selectQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        String[] columnNames = cursor.getColumnNames();

        for (String column : columnNames) {
            Log.d("1", column);
        }

        if (cursor.moveToFirst()) {
            do {
                Details details = new Details();
                details.setId(cursor.getInt(0));
                details.setLatitude(cursor.getString(1));
                details.setLongitude(cursor.getString(2));
            }
            while (cursor.moveToNext());

        }

    }
}
