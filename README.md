# marketplace

Simple implementation of LiveBoard.

Please note that this solution is not thread safe.

Each invocation of LiveBoard#getSummary calculates the summary by iterating through all the orders.

Other solutions can be implemented by using multiple data structures to make it thread safe
and also calculating summary on register/cancel orders.