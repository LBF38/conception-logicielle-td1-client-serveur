# TD 1 - Observer/Observable pattern

For this TD, we will use the Observer/Observable pattern to modify the existing code and make the UI reactive to the
data changes.

## 1. Observer/Observable pattern by heritage

In this part, we will use the Observer/Observable pattern by heritage. We will use the `Observer` and `Observable`
classes from the `java.util` package.
We used inheritance on the `automateClient` package.

Link to the relevant commit : 0456d95c0f4cacaf9ba950f9227c7f129740b044

## 2. Observer/Observable pattern by composition

In this part, we will use the Observer/Observable pattern by composition. We will use the `PropertyChangeListener`
and `PropertyChangeSupport` classes from the `java.beans` package.
We used composition on the `banqueServer` package.

Link to the relevant commit : e45cd44c6530cea21b434c6fb0b473d5d4b59bf3

(It is better to read this file on GitHub to see the links)

## Comments

The code could be improved to manage the pattern used. For example, we could create a `Notifier` class that would be
used by the `Observable` classes to notify the `Observer` classes. Therefore, we respect the Single Responsibility
Principle.
