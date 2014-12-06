#include "helloworldservice.hh"
#include "utils/String8.h"

namespace android
{
  IMPLEMENT_META_INTERFACE(HelloWorldService, "HelloWorldService");

  status_t BnHelloWorldService::onTransact(uint32_t code
					   , const Parcel& data
					   , Parcel* reply
					   , uint32_t flags)
  {
    switch (code)
    {
      case HELLO:
      {
	CHECK_INTERFACE(IHelloWorldService, data, reply);
	String8 msg = hello();
	reply->writeString8(msg);
	return NO_ERROR;
      } break;
      default:
	return BBinder::onTransact(code, data, reply, flags);
    }
  }

  BpHelloWorldService::BpHelloWorldService(const sp<IBinder>& impl)
    : BpInterface<IHelloWorldService>(impl)
  {
  }

  String8 BpHelloWorldService::hello()
  {
    Parcel data, reply;
    data.writeInterfaceToken(IHelloWorldService::getInterfaceDescriptor());
    remote()->transact(BnHelloWorldService::HELLO, data, &reply);

    String8 res(reply.readString8());
    return res;
  }

  String8 HelloWorldService::hello()
  {
    return String8("Hello World !");
  }


}
