db = db.getSiblingDB('user_db');

db.users.insert({
    _id: NumberLong(1),
    firstName: 'user1',
    lastName: 'lastname1'
});