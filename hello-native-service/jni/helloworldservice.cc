#include "helloworldservice.hh"

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
	String16 msg = hello();
	reply->writeString16(msg);
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

  String16 BpHelloWorldService::hello()
  {
    Parcel data, reply;
    data.writeInterfaceToken(IHelloWorldService::getInterfaceDescriptor());
    remote()->transact(BnHelloWorldService::HELLO, data, &reply);

    return reply.readString16();
  }

  String16 HelloWorldService::hello()
  {
    return String16("Hello World !");
  }
}
