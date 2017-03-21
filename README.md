# CheckoutKata
This repo provides an implementation for the Checkout Kata test provided.

###Requirements
The project was built and tested using Java 1.8.0_101 and Maven 3.3.9 on MacOS.

###Execution
The project uses Maven as a build tool, and can be built by typing
`mvn clean package`

###Approach
The problem presented a classic functional challenge, made slightly more complex only 
by the presence of multi-price offers. Presence of this latter made the challenge 
revolve around partition, where an a series of items had to be grouped and partitioned 
based on the multi-price rules in order to obtain the correct final total.

Java 8's Stream API came in handy to avoid unnecessary complexity by allowing 
multi-price rules to be evaluated on a single line of code.

###Structure
The solution was easy enough to be contained within a single package, however I decided
to isolate pricing-related class in a separate subpackage in order to highlight the 
relationship among them.

I could have isolated the Checkout class in a separate subpackage too, however being that
one single class I thought it would not add much value.

The challenge was easy enough not to require specific exception handling, the standard
IllegalArgumentExcpetion was sufficient to handle the case where a non-existing rule is 
trying to be applied.

###Extensibility
I decided to use one class (SpecialPrice) to model both the standard item price and 
multi-priced offers; in a real environment this would probably not be a good solution (
for instance a better one may be to model standard and quantity prices for the suitable 
offers), however in this specific case I decided that it was not necessary and keeping complexity
low was more important.