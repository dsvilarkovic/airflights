import { Component, OnInit } from "@angular/core";

@Component({
  selector: "app-friends",
  templateUrl: "./friends.component.html",
  styleUrls: ["./friends.component.scss"]
})
export class FriendsComponent implements OnInit {
  constructor() {}
  otherUsers = [
    { id: 1, firstName: "Biljan", lastName: "Ristic" },
    { id: 2, firstName: "Dragan", lastName: "Torbica" },
    { id: 3, firstName: "Zivan", lastName: "Radosavljevic" }
  ];
  friends = [
    { id: 5, firstName: "Djordje", lastName: "Cvarkov" },
    { id: 6, firstName: "Rade", lastName: "Kornjaca" },
    { id: 7, firstName: "Radenko - Tetak", lastName: "Salapura" }
  ];
  pendingFriends = [{ id: 4, firstName: "Boskic", lastName: "Mladji" }];
  pageNo = 1;
  collectionSize = 40;
  pageNo2 = 1;
  collectionSize2 = 30;
  pageNo3 = 1;
  collectionSize3 = 10;

  ngOnInit() {}

  removeFriend(friendId) {
    console.log("Remove " + friendId);
    this.friends.map(user => {
      if (user.id === friendId) this.otherUsers.push(user);
    });
    this.friends = this.friends.filter(u => {
      return friendId !== u.id;
    });
  }

  addFriend(friendId) {
    console.log("Add " + friendId);
    this.otherUsers.map(user => {
      if (user.id === friendId) this.pendingFriends.push(user);
    });
    this.otherUsers = this.otherUsers.filter(u => {
      return friendId !== u.id;
    });
  }
}
