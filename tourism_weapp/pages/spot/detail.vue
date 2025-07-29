<template>
	<view>
		<swiper class="screen-swiper square-dot" :indicator-dots="true" :circular="true" :autoplay="true"
			interval="3000" duration="500">
			<swiper-item v-for="(item,index) in spot.images" :key="index">
				<image :src="item.url" mode="aspectFill"></image>
			</swiper-item>
		</swiper>

		<view class="card main_info" style="margin-top: 0;">
			<view class="title h2">{{spot.spotName}}</view>
			<view class="openTime">
				<text class="cuIcon-time margin-right-xs"></text>
				<text>开放时间：{{spot.openingTime}}</text>
			</view>
		</view>

		<view class="card flex justify-between">
			<view class="text-bold">{{spot.address}}</view>
			<view class="flex flex-gap-sm">
				<view @click="onGuide"><text class="cuIcon-locationfill margin-right-xs"></text>地图</view>
				<view @click="callPhone"><text class="cuIcon-phone margin-right-xs"></text>电话</view>
			</view>
		</view>
	

		<view class="card" v-if="ticketList">
			<text class="h3"  v-if="ticketList.length>0">门票</text>
			<text class="h3"  v-else>免门票</text>
			<view class="radius2" v-if="ticketList.length>0">
				<view class="flex padding-sm" v-for="item in ticketList" :key="item.id"
					style="border-bottom: 1px solid #FFFFFF;background-color: #f2f2f2;"
					@click="toPreBook(item.id,item.status)">
					<view class="flex-sub margin-right-xl flex flex-direction">
						<text class="text-xl">{{item.name}}</text>
						<text class="text-grey margin-top-xs">{{item.remark||"暂无说明"}}</text>
					</view>
					<view class="flex flex-direction justify-between">
						<view class="text-right">
							<text v-if="item.status==='ONSALE'" class="cu-tag bg-green sm">在售</text>
							<text v-if="item.status==='STOPSALE'" class="cu-tag bg-red sm">停止售</text>
							<text v-if="item.status==='PRESALE'" class="cu-tag bg-grey sm">预售</text>
						</view>
						<text class="text-sm text-red margin-top-lg"><text
								class="text-xl">￥{{item.price.toFixed(1)}}</text>元/人</text>
					</view>
				</view>
			</view>
		</view>

		<view class="card">
			<text class="h3">简介</text>
			<view class="margin-bottom-sm">{{spot.description}}</view>
			<view class="flex flex-direction align-center flex-gap-sm" v-if="spot.video">
				<video style="width: 100%;" v-for="dd in spot.video" :key="dd.url" :src="dd.url" controls
					show-mute-btn></video>
			</view>
		</view>

		<view class="card" v-if="history.length>0">
			<text class="h3">历史动态</text>
			<view class="cu-timeline" v-for="item in history" :key="item.id">
				<view class="cu-time" style="display: block;width: 110px;">{{item.tracingTime}}</view>
				<view class="cu-item">
					<view class="content">
						<view class="cu-capsule radius">
							<view class="cu-tag bg-cyan">标题</view>
							<view class="cu-tag line-cyan">{{item.title}}</view>
						</view>
						<view class="margin-top-sm flex flex-gap-sm">
							<image :src="img.url" v-for="(img,index) in item.images" :key="index"
								style="width: 40px;height: 40px;border-radius: 2px;"></image>
						</view>
						<view class="margin-top">{{item.description}}</view>
					</view>
				</view>
			</view>
		</view>

		<view class="card" v-if="spot.plat.url">
			<text class="h3">景区地图</text>
			<image @longpress="savePlat" :src="spot.plat.url" style="width: 100%;height: 200px;"></image>
		</view>


		<view class="card" v-if="commentList.length>0">
			<text class="h3">景区评价</text>
			<view class="list">
				<view v-for="item in commentList" :key="item.id" class="flex padding-sm"
					style="border-bottom: 1px solid #f2f2f2;">
					<view class="flex-sub">
						<view class="margin-bottom-sm">{{item.content}}</view>
						<view class="text-grey text-xs">{{item.createTime}}</view>
					</view>
					<view>
						<view class="cu-capsule">
							<view class='cu-tag sm bg-red'>
								<text class='cuIcon-likefill'></text>
							</view>
							<view class="cu-tag sm line-red">{{item.score.toFixed(1)}}</view>
						</view>
					</view>
				</view>
			</view>
		</view>

	</view>
</template>

<script>
	import indexApi from "@/api/indexApi.js"

	export default {
		data() {
			return {
				spot: {},
				history: [],
				ticketList: [],
				commentList: []
			}
		},
		onLoad(option) {
			indexApi.getspotinfo({
				spotId: option.id
			}).then(res => {
				this.spot = res.data
			})
			indexApi.getspotheritage({
				spotId: option.id
			}).then(res => {
				this.history = res.data
			})
			indexApi.getticket({
				spotId: option.id
			}).then(res => {
				this.ticketList = res.data
				console.log("ticketList", this.ticketList);
			})
			let param = {
				current: 1,
				size: 20,
				spotId: option.id
			}
			indexApi.getcomment(param).then(res => {
				this.commentList = res.data.records
				console.log("commentList",this.commentList);
			})
		},
		methods: {
			toQifuPage(){
				uni.navigateTo({
					url:'/pages/spot/pray/pray?id='+this.spot.id
				})
			},
			toPreBook(id, status) {
				if (status !== "ONSALE") {
					uni.showToast({
						icon: 'none',
						title: '门票暂未开售'
					})
					return
				}
				uni.navigateTo({
					url: `/pages/spot/prebook?spotId=${this.spot.id}&ticketId=${id}`
				})
			},
			onGuide() {
				let latitude = Number(this.spot.latitude)
				let longitude = Number(this.spot.longitude)
				console.log(latitude, longitude);
				uni.openLocation({
					latitude,
					longitude,
					success: function() {
						console.log('success');
					}
				});
			},
			callPhone() {
				uni.makePhoneCall({
					phoneNumber: this.spot.contactPhone
				});
			},
			savePlat() {
				uni.saveImageToPhotosAlbum({
					filePath: this.spot.plat.url,
					success: (res) => {
						uni.hideLoading();
						uni.showToast({
							title: '图片已保存'
						})
					}
				})
			}
		}
	}
</script>




<style lang="scss">
	page{
		position: relative;
	}
		
	.main_info {
		background: linear-gradient(to bottom, #b4fffd, #f3ffff);
	}

	.tabbar {
		padding: 0 10px !important;
		display: grid;
		grid-template-columns: 1fr 1fr 1fr;
		grid-gap: 20px;

		button {
			font-size: 14px !important;
			height: 30px !important;
		}
	}
</style>