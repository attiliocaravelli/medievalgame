Challenge
=======================================

You are a commander under siege in an ancient medieval castle. In
this castle there is a row of cannons in a straight line defending gates to the entrance. For a given cannon numbered i, the cannons number i-1 and i+1 are adjacent, and the firers manning the adjacent cannons are denoted as "neighbours".

Your enemies sends an assassin every minute to assassinate one of your firers; upon discovery of the assassination, the dead firer's neighbours find out, and each neighbour forwards this information to his other neighbour. That other neighbour passes it on the other neighbour until they reach a firer with no other neighbour, either because he is manning cannon 1, or cannon N, or the adjacent cannon has an already dead firer. A firer, upon the news of his dead comrade, starts firing a cannonball at his enemies at the entrance of the castle. He will only fire a cannonball in retaliation per assassination. Therefore, if a firer at cannon C gets assassinated, all firers from the sides of cannon C will start firing in retaliation, until it reaches cannon 1, cannon N, or a cannon that has an already-assassinated
firer.
Suppose each cannon is manned by a firer at the start of the campaign, and your enemy will send an assassin every minute. Your spies have given you the enemy commander's hit-list of Q firers to be assassinated, but you do not know in which order he will attempt to assassinate your firers.
Your task is to figure out the maximum possibility of cannonball discharges your firers will be able to attempt, given the knowledge of the hit-list.