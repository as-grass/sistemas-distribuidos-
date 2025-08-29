JAVAC=javac
JAVA=java
PKG=rmi
SRC=$(PKG)/Sumador.java $(PKG)/SumadorImpl.java $(PKG)/SumadorServer.java $(PKG)/SumadorClient.java

.PHONY: all clean server client registry

all:
	$(JAVAC) $(SRC)

registry:
	$(JAVA) rmiregistry &

server: all
	$(JAVA) -Djava.security.policy=./policy $(PKG).SumadorServer

client: all
	$(JAVA) $(PKG).SumadorClient localhost

clean:
	rm -f $(PKG)/*.class *_Stub.class


