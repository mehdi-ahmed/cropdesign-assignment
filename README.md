# cropdesign-assignment 

Via the following link you can find a zipped xml file:
obo-xml.gz op http://www.geneontology.org/GO.downloads.ontology.shtml (legacy downloads section)
Write a java program with  user-friendly graphical interface that:

step 1: reads the xml file and displays the GO terms (term tag) in a tree structure (eg. JTree). This trees has the id of the GO term as nodes.
step 2: the additional data (def / synonym / ...) is in the subnodes of the tree.
step 3: When a certain term which has reference to an other term (<is_a> tag) and you click on this reference term tag, put the focus in the tree on the node of that other term.
