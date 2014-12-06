#ifndef HELLOWORLDSERVICE_HH_
# define HELLOWORLDSERVICE_HH_

# include <utils/RefBase.h>
# include <binder/IInterface.h>
# include <binder/Parcel.h>

namespace android
{
class IHelloWorldService : public IInterface
{
public:
  enum
  {
    HELLO
  };

  DECLARE_META_INTERFACE(HelloWorldService);
  virtual String8 hello() = 0;
};

class BnHelloWorldService : public BnInterface<IHelloWorldService>
{
public:
  virtual status_t    onTransact( uint32_t code,
					   const Parcel& data,
					   Parcel* reply,
					   uint32_t flags = 0);
};

class BpHelloWorldService : public BpInterface<IHelloWorldService>
{
public:
  BpHelloWorldService(const sp<IBinder>& );
  virtual String8 hello();
};

class HelloWorldService : public BnHelloWorldService
{
public:
  virtual String8 hello();
};
}
#endif /* !HELLOWORLDSERVICE_HH_ */
