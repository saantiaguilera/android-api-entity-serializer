Serializer & Entity

Main point of this repo is to create a base entity
from where others can inherit stuff and make easier 
POO things, since we always end up needing to create
entities for making our lifes easier.

Second point of this repo is a Serializer class. Earlier I used to
always contain the serializer inside each entity (and each entity
knew how to serialize/hidrate itself). Since we can have different 
models in the REST (for eg, it can be anywhere), that can make it
harder. Also, for memmory purposes ! If you just need to save in a 
Shared Preferences the ID of some entities (Idk, for example the
chats the user has seen), why would you serialize the whole entity ?
As a solution, there is a BaseSerializer class, from which you will
inherit a serialize() and hidrate() method which will force you to
do it. So if you need to serialize in some cases just the ID, but in 
others the whole entity, just create 2 serializers :).
Same applies if a REST api sends you from 2 different endpoints
a same object but with different JSONs (or a whole tree inside of them)

If you have hard logic inside the JSONs you are retreiving
you simply have to do it in the serializer methods. 
Beware: You can also override the serialize(List) and List::hidrate()
since they parse as if all the entities where in the root.

End note: Im working on a SharedPreferences improved for JSONs, that
works aside in a HandlerThread (or Service) im not too sure yet.
But its not that important to do it (The critical stuff were the
entity - serializer things), I will leave it as still WIP.
