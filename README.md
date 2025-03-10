# checkout
CDL Kata Checkout

To Build,
open terminal and cd to checkout directory and run mvn clean package

To Run
in terminal cd to target directory and enter java -jar checkout checkout-0.0.1-SNAPSHOT.jar


And enter something similar to,

SKU items as below, SKU Item A, Price 2.00, Quantity for Special Price 6, Special Price 10.00
---------------------------------------------------------------------------------------------
A 2.00 6 10.00

Y

B 1.00 6 5.00

Y

C 5.00 6 25.00

N when finished entering SKU Items

Now add required SKU's and quantity required,

A 4

Y

B 4

Y

C 6

Y

A 3

Y

C 2

N 

N to finish and see final order total
