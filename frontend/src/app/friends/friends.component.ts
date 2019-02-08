import { Component, OnInit } from "@angular/core";
import { FriendsService } from "src/services/friends.service";
import { UserProfileService } from "src/services/user-profile.service";

@Component({
  selector: "app-friends",
  templateUrl: "./friends.component.html",
  styleUrls: ["./friends.component.scss"]
})
export class FriendsComponent implements OnInit {
  constructor(
    private friendsService: FriendsService,
    private userService: UserProfileService
  ) {}
  otherUsers = [];
  friends = [];
  pendingFriends = [];
  acceptFriends = [];
  pageNo = 1;
  pageSize = 5;
  pageSize2 = 5;
  pageSize3 = 5;
  pageSize4 = 5;
  collectionSize = 10;
  pageNo2 = 1;
  collectionSize2 = 30;
  pageNo3 = 1;
  collectionSize3 = 10;
  pageNo4 = 1;
  collectionSize4 = 10;
  loggedUser;
  lastKeyword;

  ngOnInit() {
    this.userService.getLoggedUser().subscribe(res => {
      this.loggedUser = res;
    });
    this.friendsService.getFriends(0).subscribe(res => {
      this.friends = res.content;
      this.collectionSize2 = res.totalElements;
      this.pageSize2 = res.size;
    });
    this.friendsService.getPending(0).subscribe(res => {
      console.log(res);
      this.pendingFriends = res.content;
      this.collectionSize3 = res.totalElements;
      this.pageSize3 = res.size;
    });
    this.friendsService.getFriendRequests(0).subscribe(res => {
      console.log(res);
      this.acceptFriends = res.content;
      this.collectionSize4 = res.totalElements;
      this.pageSize4 = res.size;
    });
  }

  removeFriend(friendId) {
    console.log("Remove " + friendId);

    this.friends = this.friends.filter(u => {
      return friendId !== u.id;
    });
    this.friendsService.removeFriend(friendId).subscribe(res => {
      console.log(res);
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
    this.friendsService.sendFriendRequest({
      senderId: this.loggedUser.id,
      receiverId: friendId
    });
  }

  acceptFriend(friendId) {
    this.acceptFriends.map(user => {
      if (user.id === friendId) this.friends.push(user);
    });
    this.acceptFriends = this.acceptFriends.filter(u => {
      return friendId !== u.id;
    });
    this.friendsService.acceptFriend(friendId).subscribe(res => {
      console.log(res);
    });
  }

  refuseFriend(friendId) {
    console.log("Remove " + friendId);
    this.acceptFriends = this.acceptFriends.filter(u => {
      return friendId !== u.id;
    });
    this.friendsService.removeFriend(friendId).subscribe(res => {
      console.log(res);
    });
  }

  search(keyword) {
    this.lastKeyword = keyword;
    this.friendsService.search(0, keyword).subscribe(res => {
      console.log(res);
      this.otherUsers = res.content;
      this.collectionSize = res.totalElements;
      this.pageSize = res.size;
    });
  }

  onPageChange(pageNo) {
    this.friendsService.search(0, this.lastKeyword).subscribe(res => {
      console.log(res);
      this.otherUsers = res.content;
      this.collectionSize = res.totalElements;
      this.pageSize = res.size;
    });
  }
  onPageChange2(pageNo) {
    this.friendsService.getFriends(pageNo).subscribe(res => {
      this.friends = res.content;
      this.collectionSize2 = res.totalElements;
      this.pageSize2 = res.size;
    });
  }
  onPageChange3(pageNo) {
    this.friendsService.getPending(pageNo).subscribe(res => {
      console.log(res);
      this.pendingFriends = res.content;
      this.collectionSize3 = res.totalElements;
      this.pageSize3 = res.size;
    });
  }
  onPageChange4(pageNo) {
    this.friendsService.getFriendRequests(pageNo).subscribe(res => {
      console.log(res);
      this.acceptFriends = res.content;
      this.collectionSize4 = res.totalElements;
      this.pageSize4 = res.size;
    });
  }
}
