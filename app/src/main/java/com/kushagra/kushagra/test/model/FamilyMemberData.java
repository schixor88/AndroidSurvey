package com.kushagra.kushagra.test.model;

/**
 * Created by Prabin on 1/6/2019.
 */

public class FamilyMemberData {
    //    String mem_1_name, mem_1_cast, mem_1_gender, mem_1_age, mem_1_relationToOwner, mem_1_birthplace, mem_1_hasEmail, mem_1_hasNoEmail, mem_1_hasLeftHome6Month, mem_1_hasNotLeftHome6Month ,mem_1_leaveHome_place, mem_1_leaveHome_reason;
//    String mem_1_education, mem_1_schoolType, mem_1_leaveSchool_reason, mem_1_schoolLevel;
//    String mem_1_incomeSource, mem_1_abroad_country, mem_1_abroad_moneyTransfer, mem_1_skills, mem_1_hasBankAC, mem_1_hasNoBankAC , mem_1_hasATM, mem_1_hasNoATM,mem_1_useOnlineBanking, mem_1_notUseOnlineBanking,mem_1_regularDeposit, mem_1_notRegularDeposit, mem_1_regularDeposit_to;
//    String mem_1_isHealthy, mem_1_isDisabled, mem_1_disabilityType, mem_1_hasDisabilityCard,mem_1_hasNoDisabilityCard ,mem_1_hasDisease12Month,mem_1_noDisease12Month, mem_1_hasLongTermDisease,mem_1_noLongTermDisease, mem_1_longTermDiseaseName, mem_1_hasCommunicableDisease,mem_1_noCommunicableDisease, mem_1_usesVaccine;
//    String mem_1_socialIdentity, mem_1_socialSecurity_type, mem_1_maritalStatus, mem_1_socialInvolvements, mem_1_hasReceivedTraining, mem_1_notReceivedTraining, mem_1_isPoliticalInfluencer, mem_1_isNotPoliticalInfluencer, mem_1_politicalInfluencerLevel;
//    String mem_1_travelWork, mem_1_travelBusiness, mem_1_travelEducation, mem_1_travelOthers;

    String name, cast, gender, age, relationToOwner, birthplace, hasEmail, hasNoEmail, hasLeftHome6Month, hasNotLeftHome6Month, leaveHome_place, leaveHome_reason;
    String education, schoolType, leaveSchool_reason, schoolLevel;
    String incomeSource, abroad_country, abroad_moneyTransfer, skills, hasBankAC, hasNoBankAC, hasATM, hasNoATM, useOnlineBanking, notUseOnlineBanking, regularDeposit, notRegularDeposit, regularDeposit_to;
    String isHealthy, isDisabled, disabilityType, hasDisabilityCard, hasNoDisabilityCard, hasDisease12Month, noDisease12Month, hasLongTermDisease, noLongTermDisease, longTermDiseaseName, hasCommunicableDisease, noCommunicableDisease, communicableDiseaseName, usesVaccine;
    String socialIdentity, socialSecurity_type, maritalStatus, socialInvolvements, hasReceivedTraining, notReceivedTraining, trainingList, isPoliticalInfluencer, isNotPoliticalInfluencer, politicalInfluencerLevel;
    String travelWork, travelBusiness, travelEducation, travelOthers;

    public FamilyMemberData() {
        this.name = "0";
        this.cast = "0";
        this.gender = "0";
        this.age = "0";
        this.relationToOwner = "0";
        this.birthplace = "0";
        this.hasEmail = "0";
        this.hasNoEmail = "0";
        this.hasLeftHome6Month = "0";
        this.hasNotLeftHome6Month = "0";
        this.leaveHome_place = "0";
        this.leaveHome_reason = "0";
        this.education = "0";
        this.schoolType = "0";
        this.leaveSchool_reason = "0";
        this.schoolLevel = "0";
        this.incomeSource = "0";
        this.abroad_country = "0";
        this.abroad_moneyTransfer = "0";
        this.skills = "0";
        this.hasBankAC = "0";
        this.hasNoBankAC = "0";
        this.hasATM = "0";
        this.hasNoATM = "0";
        this.useOnlineBanking = "0";
        this.notUseOnlineBanking = "0";
        this.regularDeposit = "0";
        this.notRegularDeposit = "0";
        this.regularDeposit_to = "0";
        this.isHealthy = "0";
        this.isDisabled = "0";
        this.disabilityType = "0";
        this.hasDisabilityCard = "0";
        this.hasNoDisabilityCard = "0";
        this.hasDisease12Month = "0";
        this.noDisease12Month = "0";
        this.hasLongTermDisease = "0";
        this.noLongTermDisease = "0";
        this.longTermDiseaseName = "0";
        this.hasCommunicableDisease = "0";
        this.noCommunicableDisease = "0";
        this.communicableDiseaseName = "0";
        this.usesVaccine = "0";
        this.socialIdentity = "0";
        this.socialSecurity_type = "0";
        this.maritalStatus = "0";
        this.socialInvolvements = "0";
        this.hasReceivedTraining = "0";
        this.notReceivedTraining = "0";
        this.trainingList = "0";
        this.isPoliticalInfluencer = "0";
        this.isNotPoliticalInfluencer = "0";
        this.politicalInfluencerLevel = "0";
        this.travelWork = "0";
        this.travelBusiness = "0";
        this.travelEducation = "0";
        this.travelOthers = "0";
    }

    public String getAll() {
        return "\n\nName " + name +
                "\nCast " + cast +
                "\nGender " + gender +
                "\nAge " + age +
                "\nRelation " + relationToOwner +
                "\nBirthplace " + birthplace +
                "\nHas Email " + hasEmail +
                "\nHas No Email " + hasNoEmail +
                "\nHas left Home " + hasLeftHome6Month +
                "\nHas not left Home " + hasNotLeftHome6Month +
                "\nLeave home place " + leaveHome_place +
                "\nLeave home reason " + leaveHome_reason +
                "\n\neducation " + education +
                "\nschoolType " + schoolType +
                "\nleaveSchool_reason " + leaveSchool_reason +
                "\nschoolLevel " + schoolLevel +
                "\n\nincomeSource " + incomeSource +
                "\nabroad_country " + abroad_country +
                "\nabroad_moneyTransfer " + abroad_moneyTransfer +
                "\nskills " + skills +
                "\nhasBankAC " + hasBankAC +
                "\nhasNoBankAC " + hasNoBankAC +
                "\nhasATM " + hasATM +
                "\nhasNoATM  " + hasNoATM +
                "\nuseOnlineBanking  " + useOnlineBanking +
                "\nnotUseOnlineBanking  " + notUseOnlineBanking +
                "\nregularDeposit  " + regularDeposit +
                "\nnotRegularDeposit  " + notRegularDeposit +
                "\negularDeposit_to  " + regularDeposit_to +
                "\n\nisHealthy " + isHealthy +
                "\nisDisabled " + isDisabled +
                "\ndisabilityType " + disabilityType +
                "\nhasDisabilityCard " + hasDisabilityCard +
                "\nhasNoDisabilityCard " + hasNoDisabilityCard +
                "\nhasDisease12Month " + hasDisease12Month +
                "\nnoDisease12Month " + noDisease12Month +
                "\nhasLongTermDisease " + hasLongTermDisease +
                "\nnoLongTermDisease " + noLongTermDisease +
                "\nlongTermDiseaseName " + longTermDiseaseName +
                "\nhasCommunicableDisease " + hasCommunicableDisease +
                "\nnoCommunicableDisease " + noCommunicableDisease +
                "\ncommunicableDiseaseName " + communicableDiseaseName +
                "\nusesVaccine " + usesVaccine +
                "\n\nsocialIdentity " + socialIdentity +
                "\nsocialSecurity_type" + socialSecurity_type +
                "\nmaritalStatus " + maritalStatus +
                "\nsocialInvolvements " + socialInvolvements +
                "\nhasReceivedTraining " + hasReceivedTraining +
                "\nnotReceivedTraining " + notReceivedTraining +
                "\ntrainingList " + trainingList +
                "\nisPoliticalInfluencer " + isPoliticalInfluencer +
                "\nisNotPoliticalInfluencer " + isNotPoliticalInfluencer +
                "\npoliticalInfluencerLevel " + politicalInfluencerLevel +
                "\ntravelWork " + travelWork +
                "\ntravelBusiness " + travelBusiness +
                "\ntravelEducation " + travelEducation +
                "\ntravelOthers " + travelOthers;


    }

    public String getCommunicableDiseaseName() {
        return communicableDiseaseName;
    }

    public void setCommunicableDiseaseName(String communicableDiseaseName) {
        this.communicableDiseaseName = communicableDiseaseName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getRelationToOwner() {
        return relationToOwner;
    }

    public void setRelationToOwner(String relationToOwner) {
        this.relationToOwner = relationToOwner;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public String getHasEmail() {
        return hasEmail;
    }

    public void setHasEmail(String hasEmail) {
        this.hasEmail = hasEmail;
    }

    public String getHasNoEmail() {
        return hasNoEmail;
    }

    public void setHasNoEmail(String hasNoEmail) {
        this.hasNoEmail = hasNoEmail;
    }

    public String getHasLeftHome6Month() {
        return hasLeftHome6Month;
    }

    public void setHasLeftHome6Month(String hasLeftHome6Month) {
        this.hasLeftHome6Month = hasLeftHome6Month;
    }

    public String getHasNotLeftHome6Month() {
        return hasNotLeftHome6Month;
    }

    public void setHasNotLeftHome6Month(String hasNotLeftHome6Month) {
        this.hasNotLeftHome6Month = hasNotLeftHome6Month;
    }

    public String getLeaveHome_place() {
        return leaveHome_place;
    }

    public void setLeaveHome_place(String leaveHome_place) {
        this.leaveHome_place = leaveHome_place;
    }

    public String getLeaveHome_reason() {
        return leaveHome_reason;
    }

    public void setLeaveHome_reason(String leaveHome_reason) {
        this.leaveHome_reason = leaveHome_reason;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    public String getLeaveSchool_reason() {
        return leaveSchool_reason;
    }

    public void setLeaveSchool_reason(String leaveSchool_reason) {
        this.leaveSchool_reason = leaveSchool_reason;
    }

    public String getSchoolLevel() {
        return schoolLevel;
    }

    public void setSchoolLevel(String schoolLevel) {
        this.schoolLevel = schoolLevel;
    }

    public String getIncomeSource() {
        return incomeSource;
    }

    public void setIncomeSource(String incomeSource) {
        this.incomeSource = incomeSource;
    }

    public String getAbroad_country() {
        return abroad_country;
    }

    public void setAbroad_country(String abroad_country) {
        this.abroad_country = abroad_country;
    }

    public String getAbroad_moneyTransfer() {
        return abroad_moneyTransfer;
    }

    public void setAbroad_moneyTransfer(String abroad_moneyTransfer) {
        this.abroad_moneyTransfer = abroad_moneyTransfer;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getHasBankAC() {
        return hasBankAC;
    }

    public void setHasBankAC(String hasBankAC) {
        this.hasBankAC = hasBankAC;
    }

    public String getHasNoBankAC() {
        return hasNoBankAC;
    }

    public void setHasNoBankAC(String hasNoBankAC) {
        this.hasNoBankAC = hasNoBankAC;
    }

    public String getHasATM() {
        return hasATM;
    }

    public void setHasATM(String hasATM) {
        this.hasATM = hasATM;
    }

    public String getHasNoATM() {
        return hasNoATM;
    }

    public void setHasNoATM(String hasNoATM) {
        this.hasNoATM = hasNoATM;
    }

    public String getUseOnlineBanking() {
        return useOnlineBanking;
    }

    public void setUseOnlineBanking(String useOnlineBanking) {
        this.useOnlineBanking = useOnlineBanking;
    }

    public String getNotUseOnlineBanking() {
        return notUseOnlineBanking;
    }

    public void setNotUseOnlineBanking(String notUseOnlineBanking) {
        this.notUseOnlineBanking = notUseOnlineBanking;
    }

    public String getRegularDeposit() {
        return regularDeposit;
    }

    public void setRegularDeposit(String regularDeposit) {
        this.regularDeposit = regularDeposit;
    }

    public String getNotRegularDeposit() {
        return notRegularDeposit;
    }

    public void setNotRegularDeposit(String notRegularDeposit) {
        this.notRegularDeposit = notRegularDeposit;
    }

    public String getRegularDeposit_to() {
        return regularDeposit_to;
    }

    public void setRegularDeposit_to(String regularDeposit_to) {
        this.regularDeposit_to = regularDeposit_to;
    }

    public String getIsHealthy() {
        return isHealthy;
    }

    public void setIsHealthy(String isHealthy) {
        this.isHealthy = isHealthy;
    }

    public String getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(String isDisabled) {
        this.isDisabled = isDisabled;
    }

    public String getDisabilityType() {
        return disabilityType;
    }

    public void setDisabilityType(String disabilityType) {
        this.disabilityType = disabilityType;
    }

    public String getHasDisabilityCard() {
        return hasDisabilityCard;
    }

    public void setHasDisabilityCard(String hasDisabilityCard) {
        this.hasDisabilityCard = hasDisabilityCard;
    }

    public String getHasNoDisabilityCard() {
        return hasNoDisabilityCard;
    }

    public void setHasNoDisabilityCard(String hasNoDisabilityCard) {
        this.hasNoDisabilityCard = hasNoDisabilityCard;
    }

    public String getHasDisease12Month() {
        return hasDisease12Month;
    }

    public void setHasDisease12Month(String hasDisease12Month) {
        this.hasDisease12Month = hasDisease12Month;
    }

    public String getNoDisease12Month() {
        return noDisease12Month;
    }

    public void setNoDisease12Month(String noDisease12Month) {
        this.noDisease12Month = noDisease12Month;
    }

    public String getHasLongTermDisease() {
        return hasLongTermDisease;
    }

    public void setHasLongTermDisease(String hasLongTermDisease) {
        this.hasLongTermDisease = hasLongTermDisease;
    }

    public String getNoLongTermDisease() {
        return noLongTermDisease;
    }

    public void setNoLongTermDisease(String noLongTermDisease) {
        this.noLongTermDisease = noLongTermDisease;
    }

    public String getLongTermDiseaseName() {
        return longTermDiseaseName;
    }

    public void setLongTermDiseaseName(String longTermDiseaseName) {
        this.longTermDiseaseName = longTermDiseaseName;
    }

    public String getHasCommunicableDisease() {
        return hasCommunicableDisease;
    }

    public void setHasCommunicableDisease(String hasCommunicableDisease) {
        this.hasCommunicableDisease = hasCommunicableDisease;
    }

    public String getNoCommunicableDisease() {
        return noCommunicableDisease;
    }

    public void setNoCommunicableDisease(String noCommunicableDisease) {
        this.noCommunicableDisease = noCommunicableDisease;
    }

    public String getUsesVaccine() {
        return usesVaccine;
    }

    public void setUsesVaccine(String usesVaccine) {
        this.usesVaccine = usesVaccine;
    }

    public String getSocialIdentity() {
        return socialIdentity;
    }

    public void setSocialIdentity(String socialIdentity) {
        this.socialIdentity = socialIdentity;
    }

    public String getSocialSecurity_type() {
        return socialSecurity_type;
    }

    public void setSocialSecurity_type(String socialSecurity_type) {
        this.socialSecurity_type = socialSecurity_type;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getSocialInvolvements() {
        return socialInvolvements;
    }

    public void setSocialInvolvements(String socialInvolvements) {
        this.socialInvolvements = socialInvolvements;
    }

    public String getHasReceivedTraining() {
        return hasReceivedTraining;
    }

    public void setHasReceivedTraining(String hasReceivedTraining) {
        this.hasReceivedTraining = hasReceivedTraining;
    }

    public String getNotReceivedTraining() {
        return notReceivedTraining;
    }

    public void setNotReceivedTraining(String notReceivedTraining) {
        this.notReceivedTraining = notReceivedTraining;
    }

    public String getTrainingList() {
        return trainingList;
    }

    public void setTrainingList(String trainingList) {
        this.trainingList = trainingList;
    }

    public String getIsPoliticalInfluencer() {
        return isPoliticalInfluencer;
    }

    public void setIsPoliticalInfluencer(String isPoliticalInfluencer) {
        this.isPoliticalInfluencer = isPoliticalInfluencer;
    }

    public String getIsNotPoliticalInfluencer() {
        return isNotPoliticalInfluencer;
    }

    public void setIsNotPoliticalInfluencer(String isNotPoliticalInfluencer) {
        this.isNotPoliticalInfluencer = isNotPoliticalInfluencer;
    }

    public String getPoliticalInfluencerLevel() {
        return politicalInfluencerLevel;
    }

    public void setPoliticalInfluencerLevel(String politicalInfluencerLevel) {
        this.politicalInfluencerLevel = politicalInfluencerLevel;
    }

    public String getTravelWork() {
        return travelWork;
    }

    public void setTravelWork(String travelWork) {
        this.travelWork = travelWork;
    }

    public String getTravelBusiness() {
        return travelBusiness;
    }

    public void setTravelBusiness(String travelBusiness) {
        this.travelBusiness = travelBusiness;
    }

    public String getTravelEducation() {
        return travelEducation;
    }

    public void setTravelEducation(String travelEducation) {
        this.travelEducation = travelEducation;
    }

    public String getTravelOthers() {
        return travelOthers;
    }

    public void setTravelOthers(String travelOthers) {
        this.travelOthers = travelOthers;
    }
}