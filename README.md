# Rating and Reviews API
Providing simple ratings / reviews functionality using Spring RESTful API.

## Using the API:
- /reviews [GET] - See a list of all the reviews. Can filter by parameters, e.g. by product (productId=5)
- /reviews [POST] - create a new review.
- /reviews/{id} [PATCH] - Change the status of a review, e.g. 'published'
- /reviews/{id} [PUT] - Update a review item
- /reviews/{id} [DELETE] - delete a review by ID

Each review also has an update history.

### TODO:
- Implement tests
- Update filters mechanism
- Author as seperate entity
- Add security roles
- Add multitenancy
- Add review attributes
- Add localisation
- Add admin section