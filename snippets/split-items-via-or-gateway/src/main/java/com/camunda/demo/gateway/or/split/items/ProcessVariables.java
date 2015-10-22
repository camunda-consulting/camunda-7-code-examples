package com.camunda.demo.gateway.or.split.items;

public class ProcessVariables {

  public static final String VAR_NAME_ORDER = "order";
  public static final String VAR_NAME_APPROVED_ITEMS = "approvedItems";
  public static final String VAR_NAME_HANDLING_INFO = "handlingInfo";
  
  // Internal Variables to control process flow
  public static final String VAR_NAME_PROCESSED_ITEMS = "_processedItems";
  public static final String VAR_NAME_UNDECIDED_ITEMS = "_undecidedItemsExistant";
  public static final String VAR_NAME_NEW_APPROVED_ITEMS = "_newApprovedItems";

}
