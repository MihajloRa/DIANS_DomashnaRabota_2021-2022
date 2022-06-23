To do:
    - Implement separate Graphhopper server for route calculation
    - Dockerize OSM_Base service with Graphhopper service
    - Dockerize and populate PostGIS database by way of Dockerfile
      (would need to create postgis image and then with something similar to osm2pgsql populate the db with an .osm file)