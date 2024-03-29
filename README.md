# Searcharoo

A Command-line search application

### Installation

Requirements: [Java SE 16](https://www.oracle.com/java/technologies/javase-jdk16-downloads.html), [Gradle](https://gradle.org/install/)

``gradle build`` on the repo directory

Data files are to be added to `data` directory in the format `<entity_name>.json`

### Run

``gradle run`` on the repo directory

### Assumptions

1. Filenames of the data represent the name of the entity
2. `_id` of related entities are embedded in the format `<entity_name_id>` which points to `_id` in the entity.

### Design Decisions

1. There is enough memory to support synchronous reads of the data files.
2. There is enough memory to support the data stored in search maps. 
3. We do not need free-text search.

### Remaining Work

1. I spent more than 1 work day and unfortunately due to job and other interviews I do not have more bandwidth for the following work. But, I thought I will share it. I would appreciate the feedback including improvements regardless of how you judge this. Thanks!
2. Need to support array based fields like Tags, it is close, just need to differentiate an array object over String. Type check should do it.
3. Complete the missing tests. I did some refactoring and ended up with more tests to write. If I had more time, I would write it.
   In real world, I would ask for more time, in this situation unfortunately I don't have more badnwidth.
4. The populating of related entities, I would prefer doing it in the data loading, need to refactor for that.
